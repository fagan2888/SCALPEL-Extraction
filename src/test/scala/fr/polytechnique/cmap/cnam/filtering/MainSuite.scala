package fr.polytechnique.cmap.cnam.filtering

import java.io.File
import java.sql.Timestamp
import com.typesafe.config.ConfigFactory
import org.apache.commons.io.FileUtils
import org.apache.spark.sql.DataFrame
import fr.polytechnique.cmap.cnam.SharedContext
import fr.polytechnique.cmap.cnam.utilities.RichDataFrames

class MainSuite extends SharedContext {

  val config = ConfigFactory.parseResources("filtering.conf").getConfig("test")
  val patientsPath = config.getString("paths.output.patients")
  val eventsPath = config.getString("paths.output.events")

  override def beforeAll(): Unit = {
    FileUtils.deleteDirectory(new File(patientsPath))
    FileUtils.deleteDirectory(new File(eventsPath))
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    FileUtils.deleteDirectory(new File(patientsPath))
    FileUtils.deleteDirectory(new File(eventsPath))
    super.afterAll()
  }

  "runETL" should "correctly run the full filtering pipeline without exceptions" in {
    val sqlCtx = sqlContext
    import sqlCtx.implicits._

    // Given
    val expectedPatients: DataFrame = Seq(
      Patient(
        patientID = "Patient_01",
        gender = 2,
        birthDate = Timestamp.valueOf("1975-01-01 00:00:00"),
        deathDate = None
      ),
      Patient(
        patientID = "Patient_02",
        gender = 1,
        birthDate = Timestamp.valueOf("1959-01-01 00:00:00"),
        deathDate = Some(Timestamp.valueOf("2009-03-13 00:00:00"))
      )
    ).toDF

    val expectedFlatEvents: DataFrame = Seq(
      FlatEvent("Patient_01", 2, Timestamp.valueOf("1975-01-01 00:00:00"), None, "molecule",
        "GLICLAZIDE", 900.0, null.asInstanceOf[Timestamp], None),
      FlatEvent("Patient_01", 2, Timestamp.valueOf("1975-01-01 00:00:00"), None, "molecule",
        "GLICLAZIDE", 1800.0, Timestamp.valueOf("2006-01-15 00:00:00"), None),
      FlatEvent("Patient_01", 2, Timestamp.valueOf("1975-01-01 00:00:00"), None, "molecule",
        "GLICLAZIDE", 900.0, Timestamp.valueOf("2006-01-30 00:00:00"), None),
      FlatEvent("Patient_02", 1, Timestamp.valueOf("1959-01-01 00:00:00"),
        Some(Timestamp.valueOf("2009-03-13 00:00:00")), "molecule", "PIOGLITAZONE", 840.0,
        Timestamp.valueOf("2006-01-15 00:00:00"), None),
      FlatEvent("Patient_02", 1, Timestamp.valueOf("1959-01-01 00:00:00"),
        Some(Timestamp.valueOf("2009-03-13 00:00:00")), "molecule", "PIOGLITAZONE", 1680.0,
        Timestamp.valueOf("2006-01-30 00:00:00"), None),
      FlatEvent("Patient_02", 1, Timestamp.valueOf("1959-01-01 00:00:00"),
        Some(Timestamp.valueOf("2009-03-13 00:00:00")), "molecule", "PIOGLITAZONE", 2520.0,
        Timestamp.valueOf("2006-01-30 00:00:00"), None),
      FlatEvent("Patient_02", 1, Timestamp.valueOf("1959-01-01 00:00:00"),
        Some(Timestamp.valueOf("2009-03-13 00:00:00")), "molecule", "PIOGLITAZONE", 1680.0,
        Timestamp.valueOf("2006-01-05 00:00:00"), None),
      FlatEvent("Patient_02", 1, Timestamp.valueOf("1959-01-01 00:00:00"),
        Some(Timestamp.valueOf("2009-03-13 00:00:00")), "disease", "C67", 1.0,
        Timestamp.valueOf("2006-03-13 00:00:00"), None)
    ).toDF

    // When
    FilteringMain.runETL(sqlContext, config)

    // Then
    import RichDataFrames._
    assert(sqlCtx.read.parquet(patientsPath) === expectedPatients)
    assert(sqlCtx.read.parquet(eventsPath) === expectedFlatEvents)
  }
}
