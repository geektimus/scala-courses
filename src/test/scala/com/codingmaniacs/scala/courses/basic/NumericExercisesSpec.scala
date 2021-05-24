/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.basic

import org.specs2.mutable.Specification

class NumericExercisesSpec extends Specification {
  "The calculator" should {
    "return the factorial of a given positive integer (1)" in {
      val factorial = NumericExercises.factorial(1)
      factorial must beSome
      factorial.getOrElse(0) mustEqual 1
    }
    "return the factorial of a given positive integer (5)" in {
      val factorial = NumericExercises.factorial(5)
      factorial.getOrElse(0) mustEqual 120
    }
    "fail when provided with a non valid value (n < 1)" in {
      val factorial = NumericExercises.factorial(0)
      factorial must beNone
    }
    "return the Fibonacci of a given positive integer (1)" in {
      val fibonacci = NumericExercises.fibonacci(1)
      fibonacci mustEqual 1
    }
    "return the Fibonacci of a given positive integer (10)" in {
      val fibonacci = NumericExercises.fibonacci(10)
      fibonacci mustEqual 55
    }
  }
}
