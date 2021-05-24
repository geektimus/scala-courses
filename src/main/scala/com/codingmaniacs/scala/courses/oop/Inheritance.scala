/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.courses.oop

import scala.annotation.tailrec

object Inheritance {

  sealed abstract class MyList[+T] {
    def h: T

    def t: MyList[T]
    def isEmpty: Boolean
    def prepend[O >: T](n: O): MyList[O]

    def reverse: MyList[T]
    def ++[O >: T](ol: MyList[O]): MyList[O]
    def map[O](t: T => O): MyList[O]

    def filter(p: T => Boolean): MyList[T]
    def flapMap[O](t: T => MyList[O]): MyList[O]
    def foreach(f: T => Unit): Unit

    def sort(s: (T, T) => Int): MyList[T]
    def zipWith[O, C](o: MyList[O], f: (T, O) => C): MyList[C]
    def fold[O](i: O)(f: (O, T) => O): O

    protected def prettyPrint: String
    override def toString: String = s"[$prettyPrint]"
  }

  case object EmptyList extends MyList[Nothing] {
    override def h: Nothing = throw new NoSuchElementException

    override def t: MyList[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true

    override def prepend[O >: Nothing](n: O): MyList[O] = new CustomList(n, EmptyList)

    override def reverse: MyList[Nothing] = this

    override def ++[O >: Nothing](ol: MyList[O]): MyList[O] = ol

    override def map[O](t: Nothing => O): MyList[O] = this

    override def filter(p: Nothing => Boolean): MyList[Nothing] = this

    override def flapMap[O](t: Nothing => MyList[O]): MyList[O] = this

    override def sort(s: (Nothing, Nothing) => Int): MyList[Nothing] = this

    override def foreach(f: Nothing => Unit): Unit = ()

    override def zipWith[O, C](o: MyList[O], f: (Nothing, O) => C): MyList[C] =
      if (!o.isEmpty)
        throw new RuntimeException("The list don't have the same size")
      else
        EmptyList

    override def fold[O](start: O)(f: (O, Nothing) => O): O = start

    override protected def prettyPrint: String = ""
  }

  case class CustomList[+T](x: T, xs: MyList[T]) extends MyList[T] {
    override def h: T = x

    override def t: MyList[T] = xs

    override def isEmpty: Boolean = false

    override def prepend[O >: T](n: O): MyList[O] = new CustomList(n, this)

    override def reverse: MyList[T] = {

      @tailrec
      def reverseRec(res: MyList[T], rem: MyList[T]): MyList[T] =
        rem match {
          case l if l.isEmpty => res
          case l              => reverseRec(res.prepend(l.h), l.t)
        }
      reverseRec(EmptyList, this)
    }

    override def ++[O >: T](ol: MyList[O]): MyList[O] = new CustomList[O](h, t ++ ol)

    override def map[O](tr: T => O): MyList[O] = {

      @tailrec
      def mapRec(res: MyList[O], rem: MyList[T]): MyList[O] =
        rem match {
          case l if l.isEmpty => res
          case l              => mapRec(res.prepend(tr(l.h)), l.t)
        }
      mapRec(EmptyList, this)
    }

    override def filter(p: T => Boolean): MyList[T] = {

      @tailrec
      def filterRec(res: MyList[T], rem: MyList[T]): MyList[T] =
        rem match {
          case l if l.isEmpty => res
          case l if p(l.h)    => filterRec(res.prepend(l.h), l.t)
          case l              => filterRec(res, l.t)
        }
      filterRec(EmptyList, this)
    }

    override def flapMap[O](tr: T => MyList[O]): MyList[O] = tr(h) ++ t.flapMap(tr)

    override def prettyPrint: String = {

      @tailrec
      def toStringRec(acc: String, list: MyList[T]): String =
        list match {
          case EmptyList        => acc
          case l if acc.isEmpty => toStringRec(s"${l.h}", l.t)
          case l: CustomList[T] => toStringRec(s"$acc, ${l.h}", l.t)
        }

      toStringRec("", this)
    }

    override def foreach(f: T => Unit): Unit = {
      f(h)
      t.foreach(f)
    }

    override def sort(compare: (T, T) => Int): MyList[T] = {

      def insert(value: T, sortedList: MyList[T]): MyList[T] =
        sortedList match {
          case l if l.isEmpty                => new CustomList(value, EmptyList)
          case l if compare(value, l.h) <= 0 => new CustomList(value, l)
          case l                             => new CustomList(l.h, insert(value, l.t))
        }

      val sortedTail = t.sort(compare)
      insert(h, sortedTail)
    }

    override def zipWith[O, C](o: MyList[O], f: (T, O) => C): MyList[C] = {

      @tailrec
      def zipRec(res: MyList[C], fList: MyList[T], sList: MyList[O]): MyList[C] =
        (fList, sList) match {
          case (fl, sl) if fl.isEmpty || sl.isEmpty => res
          case (fl, sl)                             => zipRec(res.prepend(f(fl.h, sl.h)), fl.t, sl.t)
        }

      if (this.isEmpty)
        throw new RuntimeException("The list don't have the same size")
      else
        zipRec(EmptyList, this, o)
    }

    override def fold[O](start: O)(f: (O, T) => O): O = t.fold(f(start, h))(f)

  }

  object CustomList {
    def apply[T](x: T, xs: MyList[T]): MyList[T] = new CustomList(x, xs)

    def apply[T](xs: T*): MyList[T] = {

      @tailrec
      def concatRec(res: MyList[T], rem: List[T]): MyList[T] =
        rem match {
          case Nil          => res
          case List(a)      => res.prepend(a)
          case head :: tail => concatRec(res.prepend(head), tail)
        }
      concatRec(EmptyList, xs.toList)
    }

  }

}
