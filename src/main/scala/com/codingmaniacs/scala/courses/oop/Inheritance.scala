/*
 * Copyright (c) 2019 Geektimus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.codingmaniacs.scala.courses.oop

import scala.annotation.tailrec

object Inheritance {

  sealed abstract class MyList[+T] {
    def head: T
    def tail: MyList[T]
    def isEmpty: Boolean

    def prepend[O >: T](n: O): MyList[O]
    def reverse: MyList[T]

    protected def prettyPrint: String
    override def toString: String = s"[$prettyPrint]"
  }

  object EmptyList extends MyList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException

    override def tail: MyList[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true

    override def prepend[O >: Nothing](n: O): MyList[O] = new List(n, EmptyList)

    override def reverse: MyList[Nothing] = EmptyList

    override protected def prettyPrint: String = ""
  }

  class List[+T](x: T, xs: MyList[T]) extends MyList[T] {
    override def head: T = x

    override def tail: MyList[T] = xs

    override def isEmpty: Boolean = false

    override def prepend[O >: T](n: O): MyList[O] = new List(n, this)

    override def reverse: MyList[T] = {

      @tailrec
      def reverseRec(res: MyList[T], rem: MyList[T]): MyList[T] =
        rem match {
          case l if l.isEmpty => res
          case l => reverseRec(res.prepend(l.head), l.tail)
        }
      reverseRec(EmptyList, this)
    }

    override def prettyPrint: String = {

      @tailrec
      def toStringRec(acc: String, list: MyList[T]): String =
        list match {
          case l if l.isEmpty => acc
          case l if acc.isEmpty => toStringRec(s"${l.head}", l.tail)
          case l: List[T] => toStringRec(s"$acc,${l.head}", l.tail)
        }

      toStringRec("", this)
    }
  }

  object List {
    def apply[T](x: T, xs: MyList[T]): MyList[T] = new List(x, xs)

    def apply[T](xs: T*): MyList[T] = {

      @tailrec
      def concatRec(res: MyList[T], rem: Seq[T]): MyList[T] =
        rem match {
          case Seq() => res
          case Seq(head, tail @ _*) => concatRec(res.prepend(head), tail)
        }
      concatRec(EmptyList, xs)
    }
  }
}
