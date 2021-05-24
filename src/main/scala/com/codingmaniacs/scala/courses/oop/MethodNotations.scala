/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.oop

object MethodNotations {
  type Movie = String

  class Person(val name: String, val age: Int, val favoriteMovie: Movie) {
    def likes(otherMovie: Movie): Boolean = otherMovie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", age, favoriteMovie)
    def unary_+ : Person = new Person(name, age + 1, favoriteMovie)
    def unary_! : String = s"$name, what the heck?!"
    def learns(language: String): String = s"$name learns $language!"
    def learnsScala: String = learns("Scala")
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(watchCount: Int): String = s"$name watched $favoriteMovie $watchCount times"
  }

}
