/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.basic

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.Console.in

class NumericExercisesSpec extends AnyWordSpec with Matchers {
  "The calculator" should {
    "return the factorial of a given positive integer (1)" in {
      val factorial = NumericExercises.factorial(1)
      factorial.getOrElse(0) mustEqual 1
    }
    "return the factorial of a given positive integer (5)" in {
      val factorial = NumericExercises.factorial(5)
      factorial.getOrElse(0) mustEqual 120
    }
    "fail when provided with a non valid value (n < 1)" in {
      val factorial = NumericExercises.factorial(0)
      factorial mustBe None
    }
    "return the Fibonacci of a given positive integer (1)" in {
      val fibonacci = NumericExercises.fibonacci(1)
      fibonacci mustEqual 1
    }
    "return the Fibonacci of a given positive integer (10)" in {
      val fibonacci = NumericExercises.fibonacci(10)
      fibonacci mustEqual 55
    }
    "return true if a given number is prime (7607)" in {
      val isPrime = NumericExercises.isPrime(7607)
      isPrime mustBe true
    }
    "return false if a given number is not prime (16)" in {
      val isPrime = NumericExercises.isPrime(16)
      isPrime mustBe false
    }
  }
}
