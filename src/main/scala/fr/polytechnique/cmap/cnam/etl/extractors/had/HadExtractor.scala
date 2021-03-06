package fr.polytechnique.cmap.cnam.etl.extractors.had

import java.sql.Timestamp

import fr.polytechnique.cmap.cnam.etl.events.{AnyEvent, Event, EventBuilder}
import fr.polytechnique.cmap.cnam.etl.extractors.{EventRowExtractor, Extractor}
import fr.polytechnique.cmap.cnam.etl.sources.Sources
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{DataFrame, Row}

trait HadExtractor[EventType <: AnyEvent] extends Extractor[EventType] with HadSource with EventRowExtractor {

  val columnName: String

  val eventBuilder: EventBuilder

  def getInput(sources: Sources): DataFrame = sources.had.get.select(ColNames.all.map(col): _*).estimateStayStartTime

  def isInStudy(codes: Set[String])
    (row: Row): Boolean = codes.exists(code(row).startsWith(_))

  def isInExtractorScope(row: Row): Boolean = !row.isNullAt(row.fieldIndex(columnName))

  def builder(row: Row): Seq[Event[EventType]] = {
    lazy val patientId = extractPatientId(row)
    lazy val groupId = extractGroupId(row)
    lazy val eventDate = extractStart(row)
    lazy val endDate = extractEnd(row)
    lazy val weight = extractWeight(row)

    Seq(eventBuilder[EventType](patientId, groupId, code(row), weight, eventDate, endDate))
  }

  def code: Row => String = (row: Row) => row.getAs[Int](columnName).toString

  def extractPatientId(r: Row): String = {
    r.getAs[String](ColNames.PatientID)
  }

  override def extractGroupId(r: Row): String = {
    r.getAs[String](ColNames.EtaNumEpmsi) + "_" +
      r.getAs[String](ColNames.RhadNum) + "_" +
      r.getAs[Int](NewColumns.Year).toString
  }

  def extractStart(r: Row): Timestamp = r.getAs[Timestamp](NewColumns.EstimatedStayStart)
}
