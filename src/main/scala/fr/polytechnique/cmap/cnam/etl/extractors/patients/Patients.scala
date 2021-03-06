// License: BSD 3 clause

package fr.polytechnique.cmap.cnam.etl.extractors.patients

import java.sql.Timestamp
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Column, DataFrame, Dataset}
import fr.polytechnique.cmap.cnam.etl.patients._
import fr.polytechnique.cmap.cnam.etl.sources.Sources
import fr.polytechnique.cmap.cnam.util.datetime.implicits._
import fr.polytechnique.cmap.cnam.util.functions.makeTS

class Patients(config: PatientsConfig) {

  import Patients.validateDeathDate

  def extract(sources: Sources): Dataset[Patient] = {

    val dcir = sources.dcir.get
    val mco = sources.mco.get
    val irBen = sources.irBen.get

    val mcoPatients: DataFrame = McoPatients.extract(mco, config.mcoDeathCode).toDF.as("mco")

    val irBenPatients: DataFrame = IrBenPatients.extract(
      irBen, config.minYear, config.maxYear
    ).toDF.as("irBen")

    val dcirPatients: DataFrame = DcirPatients.extract(
      dcir, config.minGender, config.maxGender, config.minYear, config.maxYear
    ).toDF.as("dcir")

    import dcirPatients.sqlContext.implicits._

    val joinColumn: Column = coalesce(col("irBen.patientID"), col("mco.patientID"))

    val patients: DataFrame = irBenPatients
      .join(mcoPatients, col("irBen.patientID") === col("mco.patientID"), "outer")
      .join(dcirPatients, joinColumn === col("dcir.patientID"), "outer")

    val patientID: Column = coalesce(
      col("dcir.patientID"),
      col("irBen.patientID"),
      col("mco.patientID")
    )

    val gender: Column = coalesce(col("irBen.gender"), col("dcir.gender"))

    val birthDate: Column = coalesce(col("irBen.birthDate"), col("dcir.birthDate"))

    val deathDate: Column = coalesce(
      when(
        validateDeathDate(col("irBen.deathDate"), birthDate, config.maxYear),
        col("irBen.deathDate")
      ),
      when(
        validateDeathDate(col("dcir.deathDate"), birthDate, config.maxYear),
        col("dcir.deathDate")
      ),
      when(
        validateDeathDate(col("mco.deathDate"), birthDate, config.maxYear),
        col("mco.deathDate")
      )
    )

    val ageReferenceDate: Timestamp = config.ageReferenceDate
    val age = floor(months_between(lit(ageReferenceDate), birthDate) / 12)
    val filterPatientsByAge = age >= config.minAge && age < config.maxAge

    val filteredPatients = patients.where(filterPatientsByAge)
      .select(
        patientID.as("patientID"),
        gender.as("gender"),
        birthDate.as("birthDate"),
        deathDate.as("deathDate")
      )

    sources.mcoCe match {
      case None => filteredPatients.as[Patient]
      case Some(mcoce) =>
        val mcocePatients = McocePatients
          .extract(mcoce, config.minGender, config.maxGender, config.minYear, config.maxYear)
          .toDF()
          .as("mco_ce")

        val allPatients = filteredPatients.as("patients")
          .join(mcocePatients, col("patients.patientID") === col("mco_ce.patientID"), "full")

        val idCol = coalesce(col("patients.patientID"), col("mco_ce.patientID"))
          .alias("patientID")
        val genderCol = coalesce(col("patients.gender"), col("mco_ce.gender"))
          .alias("gender")
        val birthDateCol = coalesce(col("patients.birthDate"), col("mco_ce.birthDate"))
          .alias("birthDate")
        val deathDateCol = coalesce(col("patients.deathDate"), col("mco_ce.deathDate"))
          .alias("deathDate")

        allPatients.select(idCol, genderCol, birthDateCol, deathDateCol)
          .filter(col("patientID").isNotNull && col("gender").isNotNull && col("birthDate").isNotNull)
          .as[Patient]
    }
  }
}

object Patients {

  def validateDeathDate(deathDate: Column, birthDate: Column, maxYear: Int): Column =
    deathDate.between(birthDate, makeTS(maxYear, 1, 1))
}
