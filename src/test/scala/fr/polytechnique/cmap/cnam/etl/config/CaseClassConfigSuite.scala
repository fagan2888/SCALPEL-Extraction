// License: BSD 3 clause

package fr.polytechnique.cmap.cnam.etl.config

import org.scalatest.flatspec.AnyFlatSpec

class CaseClassConfigSuite extends AnyFlatSpec {

  "fieldsToString" should "return a string with pretty-printable information of the case class" in {

    // Given
    case class SampleCaseClass(firstField: String, secondField: List[Int]) extends CaseClassConfig
    val input = SampleCaseClass("first value", List(1, 22, 333))
    val expected: String =
      """{
        |  firstField -> first value
        |  secondField -> List(1, 22, 333)
        |}""".stripMargin

    // When
    val result = input.toPrettyString

    // Then
    assert(result == expected)
  }
}
