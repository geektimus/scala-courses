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
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ExceptionsSpec extends AnyWordSpec with Matchers {
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
      an[OverflowException] should be thrownBy PocketCalculator.add(Int.MaxValue, 10)
    }
    "throw an UnderflowException if a + b < Int.MinValue" in {
      an[UnderflowException] should be thrownBy PocketCalculator.add(-10, Int.MinValue)
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
      an[UnderflowException] should be thrownBy PocketCalculator.subtract(-20, Int.MaxValue)
    }
    "throw an OverflowException if a - b > Int.MaxValue" in {
      an[OverflowException] should be thrownBy PocketCalculator.subtract(20, Int.MinValue)
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
      an[UnderflowException] should be thrownBy PocketCalculator.subtract(-20, Int.MaxValue)
    }
    "throw an OverflowException if a * b > Int.MaxValue" in {
      an[OverflowException] should be thrownBy PocketCalculator.subtract(20, Int.MinValue)
    }
    "divide two numbers" in {
      val result = PocketCalculator.divide(200, 10)
      result mustEqual 20
    }
    "throw an MathCalculationException if b = 0 in a / b" in {
      an[MathCalculationException] should be thrownBy PocketCalculator.divide(25, 0)
    }
  }
}
