// License: BSD 3 clause

package fr.polytechnique.cmap.cnam.etl.extractors.patients

import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.TimestampType
import org.apache.spark.sql.{Column, DataFrame, Dataset}
import fr.polytechnique.cmap.cnam.etl.patients.Patient
import fr.polytechnique.cmap.cnam.util.functions.computeDateUsingMonthYear

private[patients] object IrBenPatients {

  val inputColumns = List(
    col("NUM_ENQ").as("patientID"),
    col("BEN_SEX_COD"),
    col("BEN_NAI_MOI"),
    col("BEN_NAI_ANN"),
    col("BEN_DCD_DTE")
  )

  val outputColumns = List(
    col("patientID"),
    col("gender"),
    col("birthDate"),
    col("deathDate")
  )

  implicit class IrBenPatientsDataFrame(data: DataFrame) {

    def getGender: DataFrame = {
      val result = data
        .select(
          col("patientID"),
          col("BEN_SEX_COD").cast("int").as("gender")
        ).distinct
        .cache

      val patients = result.select(col("patientID")).distinct()

      if (result.count != patients.count) {
        throw new Exception("One or more patients have conflicting SEX CODE in IR_BEN_R")
      }

      result
    }

    def getDeathDate: DataFrame = {
      data.filter(col("BEN_DCD_DTE").isNotNull)
        .groupBy(col("patientID"))
        .agg(min(col("BEN_DCD_DTE")).cast(TimestampType).as("deathDate"))
    }

    def getBirthDate(minYear: Int = 1900, maxYear: Int = 2100): DataFrame = {

      val birthDate: Column = computeDateUsingMonthYear(col("BEN_NAI_MOI"), col("BEN_NAI_ANN")).as("birthDate")

      val result = data
        .filter(
          col("BEN_NAI_MOI").between(1, 12) &&
            col("BEN_NAI_ANN").between(minYear, maxYear)
        )
        .select(col("patientID"), birthDate)
        .distinct
        .cache
      val patients = result.select(col("patientID")).distinct

      // This check makes sure patients don't have conflicting birth dates.
      if (result.count != patients.count) {
        throw new Exception("One or more patients have conflicting BIRTH DATES in IR_BEN_R")
      }

      result
    }
  }

  def extract(irBen: DataFrame, minYear: Int, maxYear: Int): Dataset[Patient] = {

    val persistedIrBen = irBen.select(inputColumns: _*).persist()
    import persistedIrBen.sqlContext.implicits._

    val birthDates = persistedIrBen.getBirthDate(minYear, maxYear)
    val deathDates = persistedIrBen.getDeathDate

    persistedIrBen.getGender
      .join(deathDates, Seq("patientID"), "left_outer")
      .join(birthDates, Seq("patientID"), "left_outer")
      .select(outputColumns: _*)
      .as[Patient]
  }
}
