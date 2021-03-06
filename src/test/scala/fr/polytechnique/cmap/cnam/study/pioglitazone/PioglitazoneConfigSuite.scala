// License: BSD 3 clause

package fr.polytechnique.cmap.cnam.study.pioglitazone

import java.nio.file.{Files, Paths}
import com.typesafe.config.ConfigFactory
import me.danielpes.spark.datetime.implicits._
import org.scalatest.flatspec.AnyFlatSpec
import fr.polytechnique.cmap.cnam.etl.config.study.StudyConfig.{InputPaths, OutputPaths}
import fr.polytechnique.cmap.cnam.etl.transformers.exposures.UnlimitedExposureAdder
import fr.polytechnique.cmap.cnam.study.pioglitazone.outcomes.CancerDefinition

class PioglitazoneConfigSuite extends AnyFlatSpec {

  val inputPaths = InputPaths(
    dcir = Some("src/test/resources/test-input/DCIR.parquet"),
    mco = Some("src/test/resources/test-input/MCO.parquet"),
    irBen = Some("src/test/resources/test-input/IR_BEN_R.parquet"),
    irImb = Some("src/test/resources/test-input/IR_IMB_R.parquet"),
    irPha = Some("src/test/resources/test-input/IR_PHA_R.parquet"),
    dosages = Some("src/test/resources/test-input/DOSE_PER_MOLECULE.CSV")
  )

  val outputPaths = OutputPaths(
    root = "target/test/output"
  )

  "load" should "correctly load the default configuration" in {

    val expected = PioglitazoneConfig(inputPaths, outputPaths)
    val config = PioglitazoneConfig.load("", "test")

    assert(config == expected)
  }

  it should "correctly load a configuration file, falling back to the default file" in {

    // Given
    val default = PioglitazoneConfig(inputPaths, outputPaths)
    val tempPath = "test.conf"
    val configContent =
      """
        | input {
        |   dcir: "new/in/path"
        | }
        | output {
        |   root: "new/out/path"
        | }
        | exposures {
        |   exposure_period_adder {
        |     min_purchases: 1
        |     purchases_window: 3 months
        |   }
        | }
        | outcomes {
        |   cancer_definition: "narrow"
        | }
      """.trim.stripMargin
    pureconfig.saveConfigAsPropertyFile(ConfigFactory.parseString(configContent), Paths.get(tempPath), true)

    val expected = default.copy(
      input = default.input.copy(
        dcir = Some("new/in/path")
      ),
      output = default.output.copy(
        root = "new/out/path"
      ),
      exposures = default.exposures.copy(
        UnlimitedExposureAdder(
          3.months,
          1,
          3.months
        )
      ),
      outcomes = default.outcomes.copy(
        cancerDefinition = CancerDefinition.Narrow
      )
    )

    // When
    val result = PioglitazoneConfig.load(tempPath, "test")

    // Then
    try {
      assert(result == expected)
    }
    finally {
      Files.delete(Paths.get(tempPath))
    }
  }
}
