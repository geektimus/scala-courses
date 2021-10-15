/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs.commands

import com.codingmaniacs.scala.exercises.fs.State
import com.codingmaniacs.scala.exercises.fs.directories.{ DirEntry, Directory }

import scala.annotation.tailrec

class Cd(dir: String) extends Command {

  override def apply(state: State): State = {
    val root = state.root

    val wd = state.workingDir

    val absPath = if (dir.startsWith(Directory.SEPARATOR)) {
      dir
    } else if (wd.isRoot) {
      wd.path + dir
    } else {
      wd.path + Directory.SEPARATOR + dir
    }

    val dstDir = doFindEntry(root, absPath)

    if (dstDir == null || !dstDir.isDirectory) {
      state.setMessage(s"$dir: Not such directory")
    } else {
      State(root, dstDir.asDirectory)
    }
  }

  def doFindEntry(root: Directory, absPath: String): DirEntry = {

    @tailrec
    def findEntryHelper(currDir: Directory, path: List[String]): DirEntry =
      if (path.isEmpty || path.head.isEmpty) currDir
      else if (path.tail.isEmpty) currDir.findEntry(path.head)
      else {
        val nextDir = currDir.findEntry(path.head)
        if (nextDir == null || !nextDir.isDirectory) null
        else findEntryHelper(nextDir.asDirectory, path.tail)
      }

    val tokens = absPath.substring(1).split(Directory.SEPARATOR).toList

    val newTokens = collapseRelativeTokens(tokens)

    if (newTokens == null) null else findEntryHelper(root, newTokens)
  }

  def collapseRelativeTokens(tokens: List[String]): List[String] = {
    @tailrec
    def collapseTokensRec(tokens: List[String], res: List[String]): List[String] =
      tokens match {
        case List()                     => res
        case List(a) => res :+ a
        case h :: tail if h.equals(".") => collapseTokensRec(tail, res)
        case h :: tail if h.equals("..") =>
          res match {
            case List()    => null
            case init :+ _ => collapseTokensRec(tail, init)
            case lst @ List(_)    => lst
          }
        case h :: tail => collapseTokensRec(tail, res :+ h)
      }

    collapseTokensRec(tokens, List())
  }

}
