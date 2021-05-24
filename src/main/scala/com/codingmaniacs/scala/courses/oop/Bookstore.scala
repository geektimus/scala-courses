/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.oop

object Bookstore {

  class Writer(val firstName: String, val surname: String = "", val yearOfBirth: Int = 0) {

    def fullname: Option[String] =
      (firstName, surname) match {
        case (f: String, s: String) if !isEmpty(f) && !isEmpty(s) => Some(s"$f $s")
        case (f, _) if !isEmpty(f)                                => Some(f)
        case _                                                    => None
      }

    private[this] val isEmpty = (x: String) => Option(x).forall(_.isEmpty)
  }

  class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {
    def authorAge: Int = yearOfRelease - author.yearOfBirth
    def isWrittenBy(otherAuthor: Writer): Boolean = otherAuthor == author
    def copy(newYearOfRelease: Int) = new Novel(name, newYearOfRelease, author)
  }

}
