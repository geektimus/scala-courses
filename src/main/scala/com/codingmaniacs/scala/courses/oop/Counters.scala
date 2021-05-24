/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.oop

object Counters {

  class Counter(val i: Int = 0) {
    def current: Int = i

    def increment: Counter = new Counter(i + 1)

    def increment(n: Int): Counter =
      n match {
        case i if i <= 0 => this
        case i           => increment.increment(i - 1)
      }

    def decrement: Counter = new Counter(i - 1)

    def decrement(n: Int): Counter =
      n match {
        case i if i <= 0 => this
        case i           => decrement.decrement(i - 1)
      }

  }

}
