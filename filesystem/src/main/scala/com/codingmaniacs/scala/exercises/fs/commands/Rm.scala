/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs.commands

import com.codingmaniacs.scala.exercises.fs.State
import com.codingmaniacs.scala.exercises.fs.directories.Directory

class Rm(name: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.workingDir

    val absPath = if (name.startsWith(Directory.SEPARATOR)) {
      name
    } else if (wd.isRoot) {
      wd.path + name
    } else {
      wd.path + Directory.SEPARATOR + name
    }

    if (Directory.ROOT_PATH.equals(absPath)) {
      state.setMessage("Function not supported yet")
    } else {
      doRm(state, absPath)
    }
  }

  def doRm(state: State, path: String): State = {
    def rmHelper(currDirectory: Directory, path: List[String]): Directory =
      if (path.isEmpty) {
        currDirectory
      } else if (path.tail.isEmpty) {
        currDirectory.removeEntry(path.head)
      } else {
        val nextDir = currDirectory.findEntry(path.head)
        if (!nextDir.isDirectory) {
          currDirectory
        } else {
          val newNextDir = rmHelper(nextDir.asDirectory, path.tail)
          if (newNextDir == nextDir) {
            currDirectory
          } else {
            currDirectory.replaceEntry(path.head, newNextDir)
          }
        }
      }

    val tokens = path.substring(1).split(Directory.SEPARATOR).toList
    val newRoot: Directory = rmHelper(state.root, tokens)

    if (newRoot == state.root) {
      state.setMessage(s"$path: No such file or directory")
    } else {
      State(newRoot, newRoot.findDescendant(state.workingDir.path.substring(1)))
    }
  }

}
