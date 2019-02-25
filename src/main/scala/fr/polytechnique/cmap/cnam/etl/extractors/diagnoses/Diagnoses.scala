package fr.polytechnique.cmap.cnam.etl.extractors.diagnoses

import fr.polytechnique.cmap.cnam.etl.events.{Diagnosis, Event}
import fr.polytechnique.cmap.cnam.etl.sources.Sources
import fr.polytechnique.cmap.cnam.util.functions.unionDatasets
import org.apache.spark.sql.Dataset


@deprecated("I said so")
class Diagnoses(config: DiagnosesConfig) {

  def extract(sources: Sources): Dataset[Event[Diagnosis]] = {

    val imbDiagnoses = ImbDiagnoses.extract(
      sources.irImb.get,
      config.imbCodes
    )
    val mcoDiagnoses = McoDiagnoses(config.dpCodes, config.drCodes, config.daCodes).extract(sources.mco.get)

    unionDatasets(imbDiagnoses, mcoDiagnoses).distinct()
  }
}
