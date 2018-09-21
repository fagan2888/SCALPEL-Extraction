package fr.polytechnique.cmap.cnam.etl.transformers.exposures

import me.danielpes.spark.datetime.Period
import me.danielpes.spark.datetime.implicits._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._


private class LimitedExposurePeriodAdder(data: DataFrame) extends ExposurePeriodAdderImpl(data) {

  import Columns._

  private val window = Window.partitionBy(col(PatientID), col(Value))
  private val orderedWindow = window.orderBy(col(Start))

  def toExposure(firstLastPurchase: DataFrame): DataFrame = {
    val condition = (col("status") === "first"
      && lead(col("status"), 1).over(orderedWindow) === "last")
    firstLastPurchase.withColumn(
      ExposureEnd,
      when(condition, lead(col("purchaseReach"), 1).over(orderedWindow))
        .otherwise(col("purchaseReach"))
    )
      .where(col("Status") === "first")
      .withColumn(ExposureStart, col("start"))
  }

  def getFirstAndLastPurchase(drugPurchases: DataFrame, endThresholdGc: Period, endThresholdNgc: Period): DataFrame = {
    val status = coalesce(
      when(col("previousPurchaseDate").isNull, "first"),
      when(col("previousPurchaseReach") < col(Start), "first"),
      when(col("purchaseReach") < col("nextPurchaseDate"), "last"),
      when(col("nextPurchaseDate").isNull, "last")
    )

    drugPurchases
      .withColumn("nextPurchaseDate", lead(col(Start), 1).over(orderedWindow))
      .withColumn("previousPurchaseDate", lag(col(Start), 1).over(orderedWindow))
      .withColumn(
        "purchaseReach",
        when(col("weight") === 1, col(Start).addPeriod(endThresholdGc))
          .otherwise(col(Start).addPeriod(endThresholdNgc))
      )
      .withColumn("previousPurchaseReach", lag(col("purchaseReach"), 1).over(orderedWindow))
      .withColumn("Status", status)
      .where(col("Status").isNotNull)
  }

  /***
    * This strategy works as the following:
    * 1. Each DrugPurchase will have a corresponding Exposure.
    * 2. Each Exposure has one or multiple DrugPurchases.
    * 3. An Exposure is defined recursively as follows:
    *   A. The first DrugPurchase defines a new Exposure.
    *   B. If there is a DrugPurchase within the defined window of the first DrugPurchase, then expand the current
    *     Exposure with the DrugPurchase.
    *   C. Else, close and set the end of the Exposure as the reach of the current and create a new Exposure with the
    *     DrugPurchase as the new Exposure.
    * This strategy is suited for short term effects.
    * !!! WARNING: THIS ONLY RETURNS EXPOSURES.
    * @param minPurchases    : Not used.
    * @param startDelay      : Not used.
    * @param purchasesWindow : Not used.
    * @param endThresholdGc  : the period that defines the reach for Grand Condtionnement.
    * @param endThresholdNgc : the period that defines the reach for Non Grand Condtionnement.
    * @param endDelay        : Not Used
    * @return: A DataFrame of Exposures.
    */
  def withStartEnd(
    minPurchases: Int = 2,
    startDelay: Period = 3.months,
    purchasesWindow: Period = 4.months,
    endThresholdGc: Option[Period] = Some(120.days),
    endThresholdNgc: Option[Period] = Some(40.days),
    endDelay: Option[Period] = Some(0.months))
  : DataFrame = {

    val outputColumns = (data.columns.toList ++ List(ExposureStart, ExposureEnd)).map(col)

    val firstLastPurchase = getFirstAndLastPurchase(data, endThresholdGc.get, endThresholdNgc.get)

    toExposure(firstLastPurchase).select(outputColumns: _*)
  }
}
