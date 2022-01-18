/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.oop

import com.codingmaniacs.scala.courses.oop.Inheritance.{ CustomList, EmptyList }
import org.mockito.Mockito.{ times, verify }
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

class InheritanceSpec extends AnyWordSpec with Matchers {
  "The custom list" should {
    "allow the creation of an EmptyList" in {
      val emptyList = EmptyList
      emptyList.isEmpty mustBe true
    }
    "allow the user to add elements to an EmptyList" in {
      val list = EmptyList.prepend(2)
      list.isEmpty mustBe false
      list.h mustEqual 2
      list.t.isEmpty mustBe true
    }
    "allow the user to create a non empty list" in {
      val list = new CustomList(1, new CustomList(2, EmptyList))
      list.isEmpty mustBe false
      list.h mustEqual 1
      val tail = list.t
      tail.h mustEqual 2
      tail.t.isEmpty mustBe true
    }
    "allow the user to reverse an empty list (duh!)" in {
      val emptyList = EmptyList
      emptyList.isEmpty mustBe true
      emptyList.reverse.isEmpty mustBe true
    }
    "allow the user to reverse a custom list" in {
      val list = new CustomList(1, new CustomList(2, EmptyList))
      list.isEmpty mustBe false
      val reversedList = list.reverse
      reversedList.h mustEqual 2
      val tail = reversedList.t
      tail.h mustEqual 1
      tail.t.isEmpty mustBe true
    }
    "allow the user to print the elements of a custom list" in {
      val list = new CustomList(1, new CustomList(2, EmptyList))
      list.isEmpty mustBe false
      list.toString mustEqual "[1, 2]"
    }
    "allow create a list in a easy way" in {
      val list = CustomList(1, 2, 3, 4, 5, 6)
      list.isEmpty mustBe false
      list.toString mustEqual "[6, 5, 4, 3, 2, 1]"
    }

    "allow the user to map a transformation on all the elements" in {
      val list = CustomList(1, 2, 3)

      val mappedList = list.map(_ * 2)

      mappedList.isEmpty mustBe false
      mappedList.h mustEqual 2
      mappedList.t.h mustEqual 4
      mappedList.t.t.h mustEqual 6
    }

    "allow the user to filter the elements of a list" in {
      val list = CustomList(1, 2, 3)

      val mappedList = list.filter(_ % 2 == 0)

      mappedList.isEmpty mustBe false
      mappedList.h mustEqual 2
      mappedList.t.isEmpty mustBe true
    }

    "allow the user to concatenate a empty list with a list" in {
      val emptyList = EmptyList
      val list = CustomList(1, 2, 3)

      val result = emptyList ++ list

      result.isEmpty mustBe false
      result.h mustEqual 3
      result.t.h mustEqual 2
    }

    "allow the user to concatenate two lists" in {
      val list = CustomList(1, 2)
      val otherList = CustomList(3, 4)

      val result = otherList ++ list

      result.isEmpty mustBe false
      result.h mustEqual 4
      result.t.h mustEqual 3
      result.t.t.h mustEqual 2
    }

    "allow the user to flatMap over a list" in {
      val list = CustomList(1, 2, 3)

      val result = list.flapMap(el => new CustomList(el, new CustomList(el * el, EmptyList)))

      result.isEmpty mustBe false
      result.h mustEqual 3
      result.t.h mustEqual 9
      result.toString mustEqual "[3, 9, 2, 4, 1, 1]"
    }

    "allow the user to sort a list (asc)" in {
      val list = CustomList(3, 5, 1, 7)

      val result = list.sort((x, y) => y - x)

      result.isEmpty mustBe false
      result.h mustEqual 7
      result.t.h mustEqual 5
      result.toString mustEqual "[7, 5, 3, 1]"
    }

    "allow the user to sort a list (desc)" in {
      val list = CustomList(3, 5, 1, 7)

      val result = list.sort((x, y) => x - y)

      result.isEmpty mustBe false
      result.h mustEqual 1
      result.t.h mustEqual 3
      result.toString mustEqual "[1, 3, 5, 7]"
    }

    "allow the user to traverse a list with foreach" in {
      val list = CustomList(3, 5, 1, 7)

      val m = mock[Int => Unit]

      list.foreach(m(_))

      verify(m, times(3))
    }

    "allow the user to zip two lists with an operation" in {
      val list1 = CustomList(3, 5, 1, 7)
      val list2 = CustomList(1, 3, 5, 7)

      val result = list1.zipWith[Int, Int](list2, (x, y) => x * y)

      result.isEmpty mustBe false
      result.toString mustEqual "[3, 15, 5, 49]"
    }

    "allow the user to zip two lists of different types with an operation" in {
      val list1 = CustomList(1, 2, 3, 4)
      val list2 = CustomList("odd", "even", "odd", "even")

      val result = list1.zipWith[String, String](list2, (x, y) => s"$x is $y")

      result.isEmpty mustBe false
      result.toString mustEqual "[1 is odd, 2 is even, 3 is odd, 4 is even]"
    }

    "allow the user to fold the elements of a list given an initial value" in {
      val list = CustomList(3, 5, 1, 7)

      val result = list.fold(0)((x, y) => x + y)

      result mustEqual 16
    }

    "allow the user to fold the elements of a list given an initial value != 0" in {
      val list = CustomList(3, 5, 1, 7)

      val result = list.fold(10)((x, y) => x + y)

      result mustEqual 26
    }
  }
}
