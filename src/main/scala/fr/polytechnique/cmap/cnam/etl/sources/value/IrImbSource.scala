// License: BSD 3 clause

package fr.polytechnique.cmap.cnam.etl.sources.value

import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{Column, DataFrame}
import fr.polytechnique.cmap.cnam.etl.sources.SourceManager

object IrImbSource extends SourceManager {

  val IMB_ALD_DTD: Column = col("IMB_ALD_DTD")

  override def sanitize(irImb: DataFrame): DataFrame = {
    irImb.where(IrImbSource.IMB_ALD_DTD =!= "")
  }
}
