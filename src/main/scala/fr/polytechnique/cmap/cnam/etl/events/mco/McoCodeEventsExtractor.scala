package fr.polytechnique.cmap.cnam.etl.events.mco

import org.apache.spark.sql.{DataFrame, Dataset, Row}
import fr.polytechnique.cmap.cnam.etl.config.ExtractionConfig
import fr.polytechnique.cmap.cnam.etl.events.{AnyEvent, Event}

object McoCodeEventsExtractor extends McoEventRowExtractor {

  def eventsFromRow(codesMap: Map[ColName, List[String]])(r: Row): List[Event[AnyEvent]] = {

    val foundCodes: Map[ColName, Option[String]] = codesMap.map {
      case (colName, codes) => colName -> extractCode(r, colName, codes)
    }

    foundCodes.collect {
      case (colName, code) if code.isDefined =>
        eventBuilder(colName).apply(
          extractPatientId(r), extractGroupId(r), code.get, extractWeight(r), extractStart(r), None
        )
    }.toList
  }

  def extract(config: ExtractionConfig, mco: DataFrame): Dataset[Event[AnyEvent]] = {

    import mco.sqlContext.implicits._
    val codesMap: Map[ColName, List[String]] = config.codesMap.map {
      case (key, value) => colNameFromConfig(key) -> value
    }
    val df = prepareDF(mco)
    df.flatMap(eventsFromRow(codesMap))
  }
}