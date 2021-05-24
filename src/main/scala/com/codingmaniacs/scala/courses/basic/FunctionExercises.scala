/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.basic

object FunctionExercises {

  def fromCurry(f: Int => Int => Int): (Int, Int) => Int = (a, b) => f(a)(b)

  def toCurry(f: (Int, Int) => Int): Int => Int => Int = a => b => f(a, b)

  def compose[A, B, C](f: A => B, g: C => A): C => B = x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))

}
