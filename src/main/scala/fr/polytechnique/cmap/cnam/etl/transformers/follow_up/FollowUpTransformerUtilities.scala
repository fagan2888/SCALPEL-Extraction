// License: BSD 3 clause

package fr.polytechnique.cmap.cnam.etl.transformers.follow_up

import java.sql.Timestamp
import fr.polytechnique.cmap.cnam.etl.transformers.follow_up.Columns.EndReasons
import fr.polytechnique.cmap.cnam.util.datetime.implicits.addMonthsToRichTimestamp

object FollowUpTransformerUtilities {

  case class PatientDates(
    patientID: String,
    deathDate: Option[Timestamp],
    followUpStart: Option[Timestamp],
    observationEnd: Option[Timestamp])

  case class TrackLossDate(
    patientID: String,
    trackloss: Option[Timestamp])

  case class FollowUpEnd(reason: String, date: Option[Timestamp])


  sealed trait EndReason {

    val endReason: String
  }

  case object death extends EndReason {
    val endReason = EndReasons
      .Death
      .toString

  }

  case object trackloss extends EndReason {
    val endReason = EndReasons
      .Trackloss
      .toString

  }

  case object observationEnd extends EndReason {
    val endReason = EndReasons
      .ObservationEnd
      .toString

  }


  abstract sealed class FollowUpEndReason(val reason: EndReason) {
    val date: Option[Timestamp]

    def compare(that: FollowUpEndReason): Int = {

      (this.date.get compareTo that.date.get) match {
        case 0 => (this.reason.endReason, that.reason.endReason) match {
          case ("Death", _) => 1
          case (_, "Death") => -1
          case ("Trackloss", _) => 1
          case (_, "Trackloss") => -1
        }
        case c => c
      }
    }
  }

  object FollowUpEndReason {

    implicit def ord[A <: FollowUpEndReason]: Ordering[A] = Ordering.by((_: A).date.get)

    implicit def ordered: Ordering[Timestamp] = new Ordering[Timestamp] {
      def compare(x: Timestamp, y: Timestamp): Int = x compareTo y
    }

  }

  case class DeathReason(
    date: Option[Timestamp]) extends FollowUpEndReason(death) with Ordered[FollowUpEndReason]

  case class TrackLossReason(
    date: Option[Timestamp]) extends FollowUpEndReason(trackloss) with Ordered[FollowUpEndReason]

  case class ObservationEndReason(
    date: Option[Timestamp]) extends FollowUpEndReason(observationEnd) with Ordered[FollowUpEndReason]

  val correctedStart: (Timestamp, Option[Timestamp], Int) => Option[Timestamp] =
    (start: Timestamp, end: Option[Timestamp], delayMonths: Int) => {
      val st: Timestamp = addMonthsToRichTimestamp(delayMonths, start)
      if (st.before(end.get)) Some(st) else None

    }

  val tracklossDateCorrected: (Timestamp, Timestamp) => Option[Timestamp] =
    (start: Timestamp, followUpStart: Timestamp) => {
      if (start.after(followUpStart)) Some(start) else None
    }

  def endReason(
    death: DeathReason,
    trackloss: TrackLossReason,
    observation: ObservationEndReason): FollowUpEnd = {
    val followUpEndReason = Seq(death, trackloss, observation).filter(e => e.date.nonEmpty).min
    FollowUpEnd(followUpEndReason.reason.endReason, followUpEndReason.date)
  }

}
