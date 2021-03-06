// License: BSD 3 clause

package fr.polytechnique.cmap.cnam.etl.sources.data

import fr.polytechnique.cmap.cnam.SharedContext

class McoCeSourceSuite extends SharedContext {
  "sanitize" should "return lines that are not corrupted" in {
    val sqlCtx = sqlContext
    import sqlCtx.implicits._

    // Given
    val colNames = List(
      McoCeSource.NIR_RET,
      McoCeSource.NAI_RET,
      McoCeSource.SEX_RET,
      McoCeSource.IAS_RET,
      McoCeSource.ENT_DAT_RET,
      McoCeSource.ETA_NUM
    ).map(col => col.toString)

    val input = Seq(
      ("1", "1", "1", "1", "1", "3333"),
      ("1", "0", "1", "0", "1", "3424"),
      ("0", "0", "0", "0", "0", "8271"),
      ("0", "0", "0", "0", "0", "9999"),
      ("0", "0", "0", "0", "0", "910100023"),
      ("0", "0", "0", "0", "0", "130784234")
    ).toDF(colNames: _*)


    val expected = Seq(
      ("0", "0", "0", "0", "0", "8271"),
      ("0", "0", "0", "0", "0", "9999")
    ).toDF(colNames: _*)

    // When
    val result = McoCeSource.sanitize(input)

    // Then
    assertDFs(result, expected)
  }
}
