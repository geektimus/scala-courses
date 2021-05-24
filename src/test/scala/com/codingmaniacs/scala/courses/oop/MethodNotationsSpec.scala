/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.oop

import com.codingmaniacs.scala.courses.oop.MethodNotations.Person
import org.specs2.mutable.Specification

class MethodNotationsSpec extends Specification {

  val person: Person = new Person("John Doe", 30, "John Wick")

  "The person class" should {
    "allow the user to check if a given person likes a movie or not" in {
      val otherMovie = "Saw 3D"
      val isFavoriteMovie = person likes otherMovie
      isFavoriteMovie must beFalse
    }
    "describe the fact that two persons are hanging out" in {
      val otherPerson = new Person("Jane Doe", 25, "Rambo III")
      val msg = person + otherPerson
      msg mustEqual "John Doe is hanging out with Jane Doe"
    }
    "show the fact that the person is shocked" in {
      val msg = !person
      msg mustEqual "John Doe, what the heck?!"
    }
    "show a short description of the person" in {
      val msg = person.apply()
      msg mustEqual "Hi, my name is John Doe and I like John Wick"
    }
    "allow the user to create a person with a nickname" in {
      val personWithNickname = person + "The unknown"
      person mustNotEqual personWithNickname
      personWithNickname.name mustEqual "John Doe (The unknown)"
    }
    "allow the user to create a person with an incremented age" in {
      val personWithNickname = +person
      person mustNotEqual personWithNickname
      personWithNickname.age mustEqual 31
    }
    "allow the user to check what a person wants to learn" in {
      (person learnsScala) mustEqual "John Doe learns Scala!"
    }
    "allow the user to say how many times a person has watched a movie" in {
      person.apply(3) mustEqual "John Doe watched John Wick 3 times"
    }
  }
}
