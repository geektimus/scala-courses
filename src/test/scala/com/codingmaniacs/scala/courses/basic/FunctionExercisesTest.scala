/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.basic

import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class FunctionExercisesTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {

  test("The function is always curried") {
    forAll { (x: Int, y: Int) =>
      val f = (x: Int, y: Int) => x + y
      val curried = FunctionExercises.toCurry(f)
      assert(curried(x)(y) == f(x, y))
    }
  }

  test("The function is always uncurried") {
    forAll { (x: Int, y: Int) =>
      val f = (x: Int) => (y: Int) => x + y
      val uncurried = FunctionExercises.fromCurry(f)
      assert(uncurried(x, y) == f(x)(y))
    }
  }

  test("We can compose two functions using compose") {
    forAll { (a: Int) =>
      val f = (x: Int) => x * 10
      val g = (x: Int) => x / 2

      val composed = FunctionExercises.compose(f, g)
      assert(composed(a) == (f compose g) (a))
    }
  }

  test("We can compose two functions using andThen") {
    forAll { (a: Int) =>
      val f = (x: Int) => x + x
      val g = (x: Int) => x * 3

      val andThen = FunctionExercises.andThen(f, g)
      assert(andThen(a) == (g andThen f) (a))
    }
  }

}
