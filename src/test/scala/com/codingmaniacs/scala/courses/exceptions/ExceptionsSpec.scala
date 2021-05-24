/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.exceptions

import com.codingmaniacs.scala.courses.exceptions.Exceptions.{
  MathCalculationException,
  OverflowException,
  PocketCalculator,
  UnderflowException
}
import org.specs2.mutable.Specification

class ExceptionsSpec extends Specification {
  "The pocket calculator" should {
    "add two positive numbers" in {
      val result = PocketCalculator.add(1, 2)
      result mustEqual 3
    }
    "add two negative numbers" in {
      val result = PocketCalculator.add(-5, -10)
      result mustEqual -15
    }
    "throw an OverflowException if a + b > Int.MaxValue" in {
      PocketCalculator.add(Int.MaxValue, 10) must throwAn[OverflowException]
    }
    "throw an UnderflowException if a + b < Int.MinValue" in {
      PocketCalculator.add(-10, Int.MinValue) must throwAn[UnderflowException]
    }
    "subtract two positive numbers" in {
      val result = PocketCalculator.subtract(1, 2)
      result mustEqual -1
    }
    "subtract two negative numbers" in {
      val result = PocketCalculator.subtract(-20, -30)
      result mustEqual 10
    }
    "throw an UnderflowException if a - b < Int.MinValue" in {
      PocketCalculator.subtract(-20, Int.MaxValue) must throwAn[UnderflowException]
    }
    "throw an OverflowException if a - b > Int.MaxValue" in {
      PocketCalculator.subtract(20, Int.MinValue) must throwAn[OverflowException]
    }
    "multiply two positive numbers" in {
      val result = PocketCalculator.multiply(5, 8)
      result mustEqual 40
    }
    "multiply two negative numbers" in {
      val result = PocketCalculator.multiply(-5, -8)
      result mustEqual 40
    }
    "throw an UnderflowException if a * b < Int.MinValue" in {
      PocketCalculator.subtract(-20, Int.MaxValue) must throwAn[UnderflowException]
    }
    "throw an OverflowException if a * b > Int.MaxValue" in {
      PocketCalculator.subtract(20, Int.MinValue) must throwAn[OverflowException]
    }
    "divide two numbers" in {
      val result = PocketCalculator.divide(200, 10)
      result mustEqual 20
    }
    "throw an MathCalculationException if b = 0 in a / b" in {
      PocketCalculator.divide(25, 0) must throwAn[MathCalculationException]
    }
  }
}
