package fr.polytechnique.cmap.cnam.etl.events.diagnoses

import fr.polytechnique.cmap.cnam.SharedContext
import fr.polytechnique.cmap.cnam.etl.config.ExtractionConfig
import fr.polytechnique.cmap.cnam.util.functions.makeTS

class ImbDiagnosesSuite extends SharedContext {

  "extract" should "extract diagnosis events from raw data" in {
    val sqlCtx = sqlContext
    import sqlCtx.implicits._

    // Given
    val config = ExtractionConfig.init().copy(imbDiagnosisCodes = List("C67"))
    val input = sqlContext.read.load("src/test/resources/test-input/IR_IMB_R.parquet")
    val expected = Seq(ImbDiagnosis("Patient_02", "C67", makeTS(2006, 3, 13))).toDS

    // When
    val output = ImbDiagnoses.extract(input, config.imbDiagnosisCodes)

    // Then
    assertDSs(expected, output)
  }
}
