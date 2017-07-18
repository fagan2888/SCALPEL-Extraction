package fr.polytechnique.cmap.cnam.etl.events.diagnoses

import java.sql.Timestamp
import org.apache.spark.sql.Row
import fr.polytechnique.cmap.cnam.etl.events._

trait Diagnosis extends AnyEvent with EventBuilder {

  val category: EventCategory[Diagnosis]

  def apply(patientID: String, code: String, date: Timestamp): Event[Diagnosis] = {
    Event(patientID, category, groupID = "NA", code, 0.0, date, None)
  }

  def apply(patientID: String, groupID: String, code: String, date: Timestamp): Event[Diagnosis] = {
    Event(patientID, category, groupID, code, 0.0, date, None)
  }

  def fromRow(r: Row, patientIDCol: String, codeCol: String, dateCol: String): Event[Diagnosis] = {
    apply(r.getAs[String](patientIDCol), r.getAs[String](codeCol), r.getAs[Timestamp](dateCol))
  }

  def fromRow(r: Row, patientIDCol: String = "patientID", groupIDCol: String = "groupID", codeCol: String = "code", dateCol: String = "eventDate"): Event[Diagnosis] = {
    apply(
      r.getAs[String](patientIDCol),
      r.getAs[String](groupIDCol),
      r.getAs[String](codeCol),
      r.getAs[Timestamp](dateCol)
    )
  }
}

object MainDiagnosis extends Diagnosis {
  override val category: EventCategory[Diagnosis] = "main_diagnosis"
}

object LinkedDiagnosis extends Diagnosis {
  override val category: EventCategory[Diagnosis] = "linked_diagnosis"
}

object AssociatedDiagnosis extends Diagnosis {
  override val category: EventCategory[Diagnosis] = "associated_diagnosis"
}

object HADMainDiagnosis extends Diagnosis {
  override val category: EventCategory[Diagnosis] = "had_main_diagnosis"
}

object SSRMainDiagnosis extends Diagnosis {
  override val category: EventCategory[Diagnosis] = "ssr_main_diagnosis"
}

object SSREtiologicDiagnosis extends Diagnosis {
  override val category: EventCategory[Diagnosis] = "ssr_etiologic_diagnosis"
}

object ImbDiagnosis extends Diagnosis {
  override val category: EventCategory[Diagnosis] = "imb_diagnosis"
}