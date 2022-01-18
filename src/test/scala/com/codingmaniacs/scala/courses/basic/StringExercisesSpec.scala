/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.basic

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StringExercisesSpec extends AnyWordSpec with Matchers {
  "The greeting function" should {
    "return a greeting when the name and age are provided" in {
      val greet = StringExercises.greet("User", 20)
      greet.getOrElse("") mustEqual "Hi, My name is User and I am 20 years old."
    }
    "fail to greet when the name is not provided" in {
      val greet = StringExercises.greet("", 20)
      greet mustBe None
    }
  }

  "The string concatenator" should {
    "return a string repeated n times (4)" in {
      val repeatedStr = StringExercises.repeat("string", 4)
      repeatedStr mustEqual "stringstringstringstring"
    }
    "return a string repeated n times (2)" in {
      val repeatedStr = StringExercises.repeat("test", 2)
      repeatedStr mustEqual "testtest"
    }
    "return the given string if the number of repetitions is non valid  (n < 0)" in {
      val repeatedStr = StringExercises.repeat("nonvalid", -1)
      repeatedStr mustEqual "nonvalid"
    }
  }
}
