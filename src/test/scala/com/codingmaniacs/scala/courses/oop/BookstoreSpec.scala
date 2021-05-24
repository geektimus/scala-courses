/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.oop

import com.codingmaniacs.scala.courses.oop.Bookstore.{ Novel, Writer }
import org.specs2.mutable.Specification

class BookstoreSpec extends Specification {
  "The bookstore" should {
    "allow the creation of a writer" in {
      val writer = new Writer("Edgar", "Poe", 1809)
      writer.fullname must beSome("Edgar Poe")
    }
    "allow the creation of a writer (with only first name)" in {
      val writer = new Writer("Edgar", yearOfBirth = 1809)
      writer.fullname must beSome("Edgar")
    }
    "avoid the creation of a writer without name" in {
      val writer = new Writer("", yearOfBirth = 1809)
      writer.fullname must beNone
    }
    "allow the creation of a novel" in {
      val novelName = "The Raven"
      val novelReleaseYear = 1845
      val allanPoe = new Writer("Edgar", "Poe", 1809)
      val novel = new Novel(novelName, novelReleaseYear, allanPoe)
      novel.name mustEqual novelName
      novel.yearOfRelease mustEqual novelReleaseYear
    }
    "allow the user to get the age of the author based on the book release date" in {
      val novelName = "The Raven"
      val novelReleaseYear = 1845
      val allanPoe = new Writer("Edgar", "Poe", 1809)
      val novel = new Novel(novelName, novelReleaseYear, allanPoe)
      novel.authorAge mustEqual 36
    }
    "allow the user to get a new revision of a book" in {
      val allanPoe = new Writer("Edgar", "Poe", 1809)
      val novel = new Novel("The Raven", 1845, allanPoe)

      val revision = novel.copy(1860)
      novel mustNotEqual revision
      novel.yearOfRelease mustNotEqual revision.yearOfRelease
      revision.authorAge mustEqual 51
    }
    "allow the user check if a book was written by a given author" in {
      val allanPoe = new Writer("Edgar", "Poe", 1809)
      val impostor = new Writer("Edgar", "Poe", 1809)
      val novel = new Novel("The Raven", 1845, allanPoe)

      val checkAuthor = novel.isWrittenBy(impostor)
      checkAuthor must beFalse
    }
  }
}
