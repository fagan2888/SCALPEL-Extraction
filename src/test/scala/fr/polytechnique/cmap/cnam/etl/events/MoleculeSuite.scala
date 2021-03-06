// License: BSD 3 clause

package fr.polytechnique.cmap.cnam.etl.events

import java.sql.Timestamp
import org.mockito.Mockito.mock
import org.scalatest.flatspec.AnyFlatSpec
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema
import org.apache.spark.sql.types._
import fr.polytechnique.cmap.cnam.util.functions.makeTS

class MoleculeSuite extends AnyFlatSpec {

  val patientID: String = "patientID"
  val timestamp: Timestamp = mock(classOf[Timestamp])

  "apply" should "allow creation of a Molecule event" in {
    // Given
    val expected = Event[Molecule.type](
      patientID, Molecule.category, "NA", "pioglitazone", 100.0, timestamp, None
    )
    // When
    val result = Molecule(patientID, "pioglitazone", 100.0, timestamp)
    // Then
    assert(result == expected)
  }

  "fromRow" should "allow creation of a Molecule event from a row object" in {
    // Given
    val schema = StructType(
      StructField("pID", StringType) ::
        StructField("mol", StringType) ::
        StructField("weight", DoubleType) ::
        StructField("date", TimestampType) :: Nil
    )
    val values = Array[Any]("Patient01", "pioglitazone", 100.0, makeTS(2010, 1, 1))
    val r = new GenericRowWithSchema(values, schema)
    val expected = Molecule("Patient01", "pioglitazone", 100.0, makeTS(2010, 1, 1))

    // When
    val result = Molecule.fromRow(r, "pID", "mol", "weight", "date")

    // Then
    assert(result == expected)
  }
}
