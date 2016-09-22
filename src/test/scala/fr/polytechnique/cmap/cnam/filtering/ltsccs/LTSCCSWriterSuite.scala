package fr.polytechnique.cmap.cnam.filtering.ltsccs

import java.io.File
import org.apache.commons.io.FileUtils
import fr.polytechnique.cmap.cnam.SharedContext
import fr.polytechnique.cmap.cnam.filtering.FlatEvent
import fr.polytechnique.cmap.cnam.utilities.RichDataFrames
import fr.polytechnique.cmap.cnam.utilities.functions._

class LTSCCSWriterSuite extends SharedContext {

  val outPath = "target/test/output/LTSCCS"

  override def beforeAll(): Unit ={
    val directory = new File(outPath)
    FileUtils.deleteDirectory(directory)
    super.beforeAll()
  }

  override def afterAll(): Unit ={
    val directory = new File(outPath)
    FileUtils.deleteDirectory(directory)
    super.afterAll()
  }

  "groundTruth" should "return a valid Dataset[GroundTruth]" in {
    val sqlCtx = sqlContext
    import LTSCCSWriter._
    import sqlCtx.implicits._

    // Given
    val input = Seq(
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "followUpPeriod",
      "disease", 900.0, makeTS(2007, 1, 1), Some(makeTS(2009, 7, 11)))
    ).toDS
    val moleculesList = List("PIOGLITAZONE", "INSULINE")
    val diseaseCodes = List("C67", "C00")

    val expected = Seq(
      GroundTruth("PIOGLITAZONE", "PIOGLITAZONE", "C67", "C67", 1),
      GroundTruth("PIOGLITAZONE", "PIOGLITAZONE", "C00", "C00", 1),
      GroundTruth("INSULINE", "INSULINE", "C67", "C67", 1),
      GroundTruth("INSULINE", "INSULINE", "C00", "C00", 1)
    ).toDS.toDF

    // When
    val result = input.groundTruth(moleculesList, diseaseCodes).toDF

    // Then
    result.printSchema
    expected.printSchema
    result.show
    expected.show
    import RichDataFrames._
    assert(result === expected)
  }

  "filterPatients" should "return only the events of the patients passed as parameter" in {
    val sqlCtx = sqlContext
    import LTSCCSWriter._
    import sqlCtx.implicits._

    // Given
    val input = Seq(
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "followUpPeriod",
        "disease", 900.0, makeTS(2007, 1, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_B", 1, makeTS(1940, 1, 1), Some(makeTS(2008, 9, 1)), "followUpPeriod",
        "trackloss", 900.0, makeTS(2006, 7, 1), Some(makeTS(2008, 9, 1))),
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "exposure",
        "PIOGLITAZONE", 1.0, makeTS(2007, 5, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "exposure",
        "SULFONYLUREA", 1.0, makeTS(2008, 8, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_B", 1, makeTS(1940, 1, 1), Some(makeTS(2008, 9, 1)), "exposure",
        "PIOGLITAZONE", 1.0, makeTS(2006, 8, 1), Some(makeTS(2008, 9, 1))),
      FlatEvent("Patient_C", 1, makeTS(1959, 1, 1), Some(makeTS(2009, 3, 13)), "disease",
        "C67", 1.0, makeTS(2008, 3, 8), None),
      FlatEvent("Patient_C", 1, makeTS(1959, 1, 1), Some(makeTS(2009, 3, 13)), "disease",
        "C67", 1.0, makeTS(2008, 3, 15), None),
      FlatEvent("Patient_C", 1, makeTS(1959, 1, 1), Some(makeTS(2009, 3, 13)), "disease",
        "C67", 1.0, makeTS(2007, 1, 29), None)
    ).toDS

    val patientIDs = Seq("Patient_A", "Patient_B").toDS

    val expected = Seq(
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "followUpPeriod",
        "disease", 900.0, makeTS(2007, 1, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_B", 1, makeTS(1940, 1, 1), Some(makeTS(2008, 9, 1)), "followUpPeriod",
        "trackloss", 900.0, makeTS(2006, 7, 1), Some(makeTS(2008, 9, 1))),
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "exposure",
        "PIOGLITAZONE", 1.0, makeTS(2007, 5, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "exposure",
        "SULFONYLUREA", 1.0, makeTS(2008, 8, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_B", 1, makeTS(1940, 1, 1), Some(makeTS(2008, 9, 1)), "exposure",
        "PIOGLITAZONE", 1.0, makeTS(2006, 8, 1), Some(makeTS(2008, 9, 1)))
    ).toDS.toDF

    // When
    val result = input.filterPatients(patientIDs).toDF

    // Then
    result.printSchema
    expected.printSchema
    result.show
    expected.show
    import RichDataFrames._
    assert(result === expected)
  }

  "toPersons" should "convert patient flat events into a Dataset[Person]" in {
    val sqlCtx = sqlContext
    import LTSCCSWriter._
    import sqlCtx.implicits._

    // Given
    val input = Seq(
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "exposure",
        "PIOGLITAZONE", 1.0, makeTS(2007, 5, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "exposure",
        "SULFONYLUREA", 1.0, makeTS(2008, 8, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_B", 1, makeTS(1940, 1, 1), Some(makeTS(2008, 9, 1)), "exposure",
        "PIOGLITAZONE", 1.0, makeTS(2006, 8, 1), Some(makeTS(2008, 9, 1)))
    ).toDS

    val expected = Seq(
      Person("Patient_A", "Patient", None, "19500101", None),
      Person("Patient_B", "Patient", None, "19400101", None)
    ).toDF

    // When
    val result = input.toPersons

    // Then
    result.printSchema
    expected.printSchema
    result.show
    expected.show
    import RichDataFrames._
    assert(result.toDF === expected)
  }

  "toObservationPeriods" should "convert observation period flat events into a Dataset[ObservationPeriod]" in {
    val sqlCtx = sqlContext
    import LTSCCSWriter._
    import sqlCtx.implicits._

    // Given
    val input = Seq(
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "observationPeriod",
        "death", 1.0, makeTS(2006, 7, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_B", 1, makeTS(1940, 1, 1), Some(makeTS(2008, 9, 1)), "observationPeriod",
        "disease", 1.0, makeTS(2006, 8, 1), Some(makeTS(2008, 9, 1)))
    ).toDS

    val expected = Seq(
      ObservationPeriod("Patient_A", "ObsPeriod", None, "20060701", "20090711"),
      ObservationPeriod("Patient_B", "ObsPeriod", None, "20060801", "20080901")
    ).toDF

    // When
    val result = input.toObservationPeriods

    // Then
    result.printSchema
    expected.printSchema
    result.show
    expected.show
    import RichDataFrames._
    assert(result.toDF === expected)
  }

  "toDrugExposures" should "convert exposure flat events into a Dataset[DrugExposure]" in {
    val sqlCtx = sqlContext
    import LTSCCSWriter._
    import sqlCtx.implicits._

    // Given
    val input = Seq(
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "exposure",
        "PIOGLITAZONE", 1.0, makeTS(2007, 5, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "exposure",
        "SULFONYLUREA", 1.0, makeTS(2008, 8, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_B", 1, makeTS(1940, 1, 1), Some(makeTS(2008, 9, 1)), "exposure",
        "PIOGLITAZONE", 1.0, makeTS(2006, 8, 1), Some(makeTS(2008, 9, 1)))
    ).toDS

    val expected = Seq(
      DrugExposure("Patient_A", "Rx", "PIOGLITAZONE", "20070501", "20090711"),
      DrugExposure("Patient_A", "Rx", "SULFONYLUREA", "20080801", "20090711"),
      DrugExposure("Patient_B", "Rx", "PIOGLITAZONE", "20060801", "20080901")
    ).toDF

    // When
    val result = input.toDrugExposures

    // Then
    result.printSchema
    expected.printSchema
    result.show
    expected.show
    import RichDataFrames._
    assert(result.toDF === expected)
  }

  "toConditionEras" should "convert disease flat events into a Dataset[DrugExposure]" in {
    val sqlCtx = sqlContext
    import LTSCCSWriter._
    import sqlCtx.implicits._

    // Given
    val input = Seq(
      FlatEvent("Patient_A", 1, makeTS(1975, 1, 1), None, "disease",
        "C67", 1.0, makeTS(2006, 7, 5), None),
      FlatEvent("Patient_B", 1, makeTS(1959, 1, 1), Some(makeTS(2009, 3, 13)), "disease",
        "C67", 1.0, makeTS(2006, 3, 13), None),
      FlatEvent("Patient_C", 1, makeTS(1959, 1, 1), Some(makeTS(2009, 3, 13)), "disease",
        "C67", 1.0, makeTS(2005, 12, 29), None)
    ).toDS

    val expected = Seq(
      ConditionEra("Patient_A", "Condition", "C67", "20060705", "20060705"),
      ConditionEra("Patient_B", "Condition", "C67", "20060313", "20060313")
    ).toDF

    // When
    val result = input.toConditionEras

    // Then
    result.printSchema
    expected.printSchema
    result.show
    expected.show
    import RichDataFrames._
    assert(result.toDF === expected)
  }

  "writeLTSCCS" should "write all 5 files with LTSCCS features" in {
    val sqlCtx = sqlContext
    import LTSCCSWriter._
    import sqlCtx.implicits._

    // Given
    val input = Seq(
      FlatEvent("Patient_A", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "observationPeriod",
        "disease", 900.0, makeTS(2007, 1, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_B", 1, makeTS(1940, 1, 1), Some(makeTS(2008, 9, 1)), "observationPeriod",
        "trackloss", 900.0, makeTS(2006, 7, 1), Some(makeTS(2008, 9, 1))),
      FlatEvent("Patient_C", 1, makeTS(1940, 1, 1), Some(makeTS(2008, 9, 1)), "observationPeriod",
        "trackloss", 900.0, makeTS(2006, 7, 1), Some(makeTS(2008, 9, 1))),
      FlatEvent("Patient_D", 1, makeTS(1940, 1, 1), Some(makeTS(2008, 9, 1)), "observationPeriod",
        "trackloss", 900.0, makeTS(2006, 7, 1), Some(makeTS(2008, 9, 1))),
      FlatEvent("Patient_A", 1, makeTS(1960, 1, 1), Some(makeTS(2009, 7, 11)), "exposure",
        "PIOGLITAZONE", 1.0, makeTS(2007, 5, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_A", 1, makeTS(1960, 1, 1), Some(makeTS(2009, 7, 11)), "exposure",
        "SULFONYLUREA", 1.0, makeTS(2008, 2, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_B", 1, makeTS(1950, 1, 1), Some(makeTS(2009, 7, 11)), "exposure",
        "SULFONYLUREA", 1.0, makeTS(2008, 8, 1), Some(makeTS(2009, 7, 11))),
      FlatEvent("Patient_C", 1, makeTS(1940, 1, 1), Some(makeTS(2008, 9, 1)), "exposure",
        "PIOGLITAZONE", 1.0, makeTS(2006, 8, 1), Some(makeTS(2008, 9, 1))),
      FlatEvent("Patient_C", 1, makeTS(1960, 1, 1), Some(makeTS(2008, 9, 1)), "disease",
        "C67", 1.0, makeTS(2008, 3, 8), None),
      FlatEvent("Patient_C", 1, makeTS(1960, 1, 1), Some(makeTS(2008, 9, 1)), "disease",
        "C67", 1.0, makeTS(2008, 3, 15), None),
      FlatEvent("Patient_C", 1, makeTS(1960, 1, 1), Some(makeTS(2008, 9, 1)), "disease",
        "C67", 1.0, makeTS(2007, 1, 29), None)
    ).toDS

    def readFile(path: String) = {
      sqlCtx.read.format("com.databricks.spark.csv").option("header", "true").load(path)
    }
    val expectedCounts = List(1, 2, 2, 2, 3, 1, 2, 2, 2, 0)

    // When
    input.writeLTSCCS(outPath)
    val resultedCounts = List(
      s"$outPath/all/PIOGLITAZONE/GroundTruth.csv",
      s"$outPath/all/PIOGLITAZONE/Persons.txt",
      s"$outPath/all/PIOGLITAZONE/Observationperiods.txt",
      s"$outPath/all/PIOGLITAZONE/Drugexposures.txt",
      s"$outPath/all/PIOGLITAZONE/Conditioneras.txt",
      s"$outPath/all/SULFONYLUREA/GroundTruth.csv",
      s"$outPath/all/SULFONYLUREA/Persons.txt",
      s"$outPath/all/SULFONYLUREA/Observationperiods.txt",
      s"$outPath/all/SULFONYLUREA/Drugexposures.txt",
      s"$outPath/all/SULFONYLUREA/Conditioneras.txt"
    ).map(p => readFile(p).count)

    // Then
    assert(resultedCounts == expectedCounts)
  }
}
