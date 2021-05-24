/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.oop

import com.codingmaniacs.scala.courses.oop.Counters.Counter
import org.specs2.mutable.Specification

class CountersSpec extends Specification {
  "The counter class" should {
    "allow the creation of a counter" in {
      val counter = new Counter
      counter.i mustEqual 0
    }
    "allow the user to increment a counter by 1" in {
      val counter = new Counter
      val newCounter = counter.increment.increment
      newCounter.i mustEqual 2
    }

    "allow the user to increment a counter by n" in {
      val counter = new Counter
      val newCounter = counter.increment(10)
      newCounter.i mustEqual 10
    }

    "allow the user to decrement a counter by 1" in {
      val counter = new Counter
      val newCounter = counter.increment.increment.decrement
      newCounter.i mustEqual 1
    }

    "allow the user to decrement a counter by n" in {
      val counter = new Counter
      val newCounter = counter.increment(10).decrement(5)
      newCounter.i mustEqual 5
    }
  }
}
