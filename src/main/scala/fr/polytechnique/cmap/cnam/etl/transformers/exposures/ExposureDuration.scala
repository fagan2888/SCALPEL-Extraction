// License: BSD 3 clause

package fr.polytechnique.cmap.cnam.etl.transformers.exposures

import me.danielpes.spark.datetime.implicits._
import me.danielpes.spark.datetime.{Period => Duration}
import fr.polytechnique.cmap.cnam.etl.datatypes._
import fr.polytechnique.cmap.cnam.etl.events.{Event, Exposure}


case class ExposureDuration(patientID: String, value: String, period: Period, span: Long)
  extends Addable[ExposureDuration] {
  self =>
  override def +(other: ExposureDuration): RemainingPeriod[ExposureDuration] =
    if ((self.patientID != other.patientID) | (self.value != other.value)) {
      RightRemainingPeriod(self)
    } else {
      self.period + other.period match {
        case RightRemainingPeriod(p) =>
          RightRemainingPeriod(ExposureDuration(self.patientID, self.value, p, self.span + other.span))

        case DisjointedRemainingPeriod(LeftRemainingPeriod(p1), RightRemainingPeriod(p2)) =>
          if (p1 == self.period) {
            DisjointedRemainingPeriod(
              LeftRemainingPeriod(ExposureDuration(self.patientID, self.value, p1, self.span)),
              RightRemainingPeriod(ExposureDuration(other.patientID, other.value, p2, other.span))
            )
          } else {
            DisjointedRemainingPeriod(
              LeftRemainingPeriod(ExposureDuration(other.patientID, other.value, p1, other.span)),
              RightRemainingPeriod(ExposureDuration(self.patientID, self.value, p2, self.span))
            )
          }
      }
    }

  def toExposure: Event[Exposure] =
    Exposure(self.patientID, self.value, 1D, self.period.start, self.period.start + Duration(milliseconds = span) get)
}

sealed trait ExposureDurationStrategy extends Function1[ExposureDuration, Event[Exposure]] with Serializable

object PurchaseCountBased extends ExposureDurationStrategy {
  override def apply(v1: ExposureDuration): Event[Exposure] = {
    Exposure(v1.patientID, v1.value, 1D, v1.period.start, v1.period.start + Duration(milliseconds = v1.span) get)
  }
}

object LatestPurchaseBased extends ExposureDurationStrategy {
  override def apply(v1: ExposureDuration): Event[Exposure] = {
    Exposure(v1.patientID, "NA", v1.value, 1D, v1.period.start, Some(v1.period.end))
  }
}