package fr.polytechnique.cmap.cnam.etl.extractors.patients

import java.sql.Timestamp

import fr.polytechnique.cmap.cnam.SharedContext
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class HadPatientsSuite extends SharedContext {

  import fr.polytechnique.cmap.cnam.etl.extractors.patients.HadPatients.HadPatientsDataFrame

  "getDeathDates" should "collect death dates correctly from flat HAD" in {
    val sqlCtx = sqlContext
    import sqlCtx.implicits._

    // Given
    val input: DataFrame = Seq(
      ("Patient_01", 1, 2, 1983),
      ("Patient_02", 9, 3, 1986)
    ).toDF("patientID", "SOR_MOD", "SOR_MOI", "SOR_ANN")

    val expected: DataFrame = Seq(
      ("Patient_02", Timestamp.valueOf("1986-03-01 00:00:00"))
    ).toDF("patientID", "deathDate")

    // When
    val result: DataFrame = input.getDeathDates(9).select(col("patientID"), col("deathDate"))

    // Then
    assertDFs(result, expected)
  }

  it should "choose minimum death date if a patient has more than one death dates" in {
    val sqlCtx = sqlContext
    import sqlCtx.implicits._

    // Given
    val input: DataFrame = Seq(
      ("Patient_01", 9, 2, 1985),
      ("Patient_01", 9, 4, 1980)
    ).toDF("patientID", "SOR_MOD", "SOR_MOI", "SOR_ANN")

    val expected: DataFrame = Seq(
      ("Patient_01", Timestamp.valueOf("1980-04-01 00:00:00"))
    ).toDF("patientID", "deathDate")

    // When
    val result: DataFrame = input.getDeathDates(9).select(col("patientID"), col("deathDate"))

    // Then
    assertDFs(result, expected)
  }

  "transform" should "return correct Dataset" in {
    val sqlCtx = sqlContext
    import sqlCtx.implicits._

    // Given
    val had: DataFrame = Seq(
      ("Patient_01", 1, 2, 1980),
      ("Patient_02", 9, 3, 1986),
      ("Patient_03", 9, 4, 1980),
      ("Patient_03", 9, 4, 1984),
      ("Patient_04", 3, 5, 1995)
    ).toDF("NUM_ENQ", "HAD_B__SOR_MOD", "HAD_B__SOR_MOI", "HAD_B__SOR_ANN")

    val expected: DataFrame = Seq(
      ("Patient_02", Timestamp.valueOf("1986-03-01 00:00:00")),
      ("Patient_03", Timestamp.valueOf("1980-04-01 00:00:00"))
    ).toDF("patientID", "deathDate")

    // When
    val result = HadPatients.extract(had)

    // Then
    assertDFs(result.toDF, expected)
  }

  "extract" should "extract target HadPatients" in {
    val sqlCtx = sqlContext
    import sqlCtx.implicits._

    // Given
    val had = spark.read.parquet("src/test/resources/test-input/HAD.parquet")

    val result = HadPatients.extract(had)

    val expected: DataFrame = Seq.empty[
      (String, Timestamp)
    ].toDF("patientID", "deathDate")

    // Then
    assertDSs(result, expected)
  }
}