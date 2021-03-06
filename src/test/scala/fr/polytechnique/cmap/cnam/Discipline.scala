// License: BSD 3 clause

package fr.polytechnique.cmap.cnam

import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatestplus.scalacheck.Checkers
import org.typelevel.discipline.Laws

trait Discipline extends Checkers {
  self: AnyFunSuiteLike =>

  def checkAll(name: String, ruleSet: Laws#RuleSet): Unit = {
    for ((id, prop) <- ruleSet.all.properties)
      registerTest(s"${name}.${id}") {
        check(prop)
      }
  }

}
