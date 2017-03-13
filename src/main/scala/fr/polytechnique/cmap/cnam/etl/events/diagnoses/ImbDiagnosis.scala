package fr.polytechnique.cmap.cnam.etl.events.diagnoses

import fr.polytechnique.cmap.cnam.etl.events.EventCategory

private[diagnoses] object ImbDiagnosis extends DiagnosisBuilder {
  val category: EventCategory[DiagnosisBuilder] = "imb_diagnosis"
}
