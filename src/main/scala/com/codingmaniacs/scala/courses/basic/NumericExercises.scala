/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.basic

import scala.annotation.tailrec

object NumericExercises {

  val factorial: Int => Option[BigInt] = (n: Int) => {

    @tailrec
    def factRec(acc: Int, number: Int): Option[BigInt] =
      number match {
        case i if i < 1 => None
        case 1          => Some(acc)
        case i          => factRec(acc * i, i - 1)
      }

    factRec(1, n)
  }

  val fibonacci: Int => BigInt = (n: Int) => {

    @tailrec
    def fibonacciRec(i: Int, prev: Int, current: Int): BigInt =
      if (i <= 0) {
        current
      } else {
        fibonacciRec(i - 1, prev = prev + current, current = prev)
      }

    fibonacciRec(n, 1, 0)
  }

}
