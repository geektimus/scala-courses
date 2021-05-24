/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.basic

import scala.annotation.tailrec

object StringExercises {

  val greet: (String, Int) => Option[String] = (username: String, age: Int) =>
    (username, age) match {
      case (s, _) if s == null || s.trim.isEmpty => None
      case (s, n) if n > 0                       => Some(s"Hi, My name is $s and I am $n years old.")
      case _                                     => None
    }

  def repeat(str: String, i: Int): String = {

    @tailrec
    def repeatRec(res: String, input: String, count: Int): String =
      count match {
        case 0           => res
        case n if n >= 0 => repeatRec(res + input, input, n - 1)
        case _           => input
      }
    repeatRec("", str, i)
  }

}
