package fr.polytechnique.cmap.cnam.etl.extractors.acts

import java.sql.Timestamp
import scala.util.Try
import org.apache.spark.sql.{DataFrame, Row}
import fr.polytechnique.cmap.cnam.etl.events.{DcirAct, Event, MedicalAct}
import fr.polytechnique.cmap.cnam.etl.extractors.Extractor
import fr.polytechnique.cmap.cnam.etl.sources.Sources
import fr.polytechnique.cmap.cnam.util.datetime.implicits._

object NewDcirMedicalActExtractor extends Extractor[MedicalAct] {

  override def getInput(sources: Sources): DataFrame = sources.dcir.get

  override def isInStudy(codes: Set[String])
    (row: Row): Boolean = codes.exists {
    val idx = row.fieldIndex(ColNames.CamCode)
    row.getString(idx).startsWith(_)
  }

  override def isInExtractorScope(row: Row): Boolean = !row.isNullAt(row.fieldIndex(ColNames.CamCode))

  override def builder(row: Row): Seq[Event[MedicalAct]] = {
    val groupID = {
      getGroupId(row) recover { case _: IllegalArgumentException => DcirAct.groupID.DcirAct }
    }

    Seq(
      DcirAct
        .apply(
          patientID = getPatientID(row),
          groupID = groupID.get,
          code = getCode(row),
          date = getDate(row).getOrElse(new java.util.Date().toTimestamp)
        )
    )

  }

  private final object ColNames {
    lazy val PatientID: String = "NUM_ENQ"
    lazy val CamCode: String = "ER_CAM_F__CAM_PRS_IDE"
    lazy val GHSCode: String = "ER_ETE_F__ETE_GHS_NUM"
    lazy val InstitutionCode: String = "ER_ETE_F__ETE_TYP_COD"
    lazy val Sector: String = "ER_ETE_F__PRS_PPU_SEC"
    lazy val Date: String = "EXE_SOI_DTD"
  }

  private final val PrivateInstitutionCodes = List(4, 5, 6, 7)

  /**
    * Get the information of the origin of DCIR act that is being extracted. It returns a
    * Failure[IllegalArgumentException] if the DCIR schema is old, a success if the DCIR schema contains an information.
    *
    * @param r the row of DCIR to be investigated.
    * @return Try[String]
    */
  def getGroupId(r: Row): Try[String] = Try {

    // First delete Public stuff
    if (!r.isNullAt(r.fieldIndex(ColNames.Sector)) && getSector(r) != 2) {
      DcirAct.groupID.PublicAmbulatory
    }
    else {
      // If the value is at null, then it is liberal
      if (r.isNullAt(r.fieldIndex(ColNames.GHSCode))) {
        DcirAct.groupID.Liberal
      } else {
        // Value is not at null, it is not liberal
        lazy val ghs = getGHS(r)
        lazy val institutionCode = getInstitutionCode(r)
        // Check if it is a private ambulatory
        if (ghs == 0 && PrivateInstitutionCodes.contains(institutionCode)) {
          DcirAct.groupID.PrivateAmbulatory
        }
        else {
          DcirAct.groupID.Unknown
        } // Non-liberal, non-Private ambulatory and non-public
      }
    }
  }

  def getGHS(r: Row): Double = r.getAs[Double](ColNames.GHSCode)

  def getInstitutionCode(r: Row): Double = r.getAs[Double](ColNames.InstitutionCode)

  def getSector(r: Row): Double = r.getAs[Double](ColNames.Sector)

  def getDate(r: Row): Try[Timestamp] = Try(r.getAs[java.util.Date](ColNames.Date).toTimestamp)

  def getCode(r: Row): String = r.getAs[String](ColNames.CamCode)

  def getPatientID(r: Row): String = r.getAs[String](ColNames.PatientID)

}