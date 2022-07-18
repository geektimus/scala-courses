/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.exceptions

object Exceptions {

  case class OverflowException(message: String) extends RuntimeException
  case class UnderflowException(message: String) extends RuntimeException
  case class MathCalculationException(message: String) extends RuntimeException

  object PocketCalculator {

    val add: (Int, Int) => Int = (a: Int, b: Int) =>
      a + b match {
        case r if a > 0 && b > 0 && r < 0 =>
          throw OverflowException("The result is bigger than Int.MaxValue")
        case r if a < 0 && b < 0 && r > 0 =>
          throw UnderflowException("The result is smaller than Int.MinValue")
        case r => r
      }

    val subtract: (Int, Int) => Int = (a: Int, b: Int) =>
      a - b match {
        case r if a > 0 && b < 0 && r < 0 =>
          throw OverflowException("The result is bigger than Int.MaxValue")
        case r if a < 0 && b > 0 && r > 0 =>
          throw UnderflowException("The result could be smaller than Int.MinValue")
        case r => r
      }

    val multiply: (Int, Int) => Int = (a: Int, b: Int) =>
      a * b match {
        case r if a > 0 && b > 0 && r < 0 =>
          throw OverflowException("The result is bigger than Int.MaxValue")
        case r if a > 0 && b < 0 && r > 0 =>
          throw UnderflowException("The result is smaller than Int.MaxValue")
        case r if a < 0 && b > 0 && r > 0 =>
          throw UnderflowException("The result is smaller than Int.MaxValue")
        case r => r
      }

    val divide: (Int, Int) => Int = (a: Int, b: Int) =>
      (a, b) match {
        case (_, 0) => throw MathCalculationException("Division by zero is not allowed")
        case (n, m) => n / m
      }

  }

}
