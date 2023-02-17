/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs.commands

import com.codingmaniacs.scala.exercises.fs.State
import com.codingmaniacs.scala.exercises.fs.directories.Directory
import com.codingmaniacs.scala.exercises.fs.files.File

import scala.annotation.tailrec

class Echo(items: Array[String]) extends Command {

  def createContent(items: Array[String], topIndex: Int): String = {

    @tailrec
    def createContentRec(currIdx: Int, acc: String): String =
      currIdx match {
        case i if i >= topIndex => acc
        case i                  => createContentRec(i + 1, acc + items(currIdx))
      }

    createContentRec(0, "")
  }

  def getRootAfterEcho(
    currentDir: Directory,
    path: List[String],
    contents: String,
    append: Boolean
  ): Directory =
    if (path.isEmpty) {
      currentDir
    } else if (path.tail.isEmpty) {
      val dirEntry = currentDir.findEntry(path.head)
      if (dirEntry == null) {
        currentDir.addEntry(new File(currentDir.path, path.head, contents))
      } else if (dirEntry.isDirectory) {
        currentDir
      } else {
        if (append) {
          currentDir.replaceEntry(path.head, dirEntry.asFile.appendContents(contents))
        } else {
          currentDir.replaceEntry(path.head, dirEntry.asFile.setContents(contents))
        }
      }
    } else {
      val nextDir = currentDir.findEntry(path.head).asDirectory
      val newNextDir = getRootAfterEcho(nextDir, path.tail, contents, append)

      if (newNextDir == nextDir) {
        currentDir
      } else {
        currentDir.replaceEntry(path.head, newNextDir)
      }
    }

  def doEcho(state: State, contents: String, filename: String, append: Boolean): State =
    if (filename.contains(Directory.SEPARATOR)) {
      state.setMessage("Echo: Filename must not contain separators")
    } else {
      val newRoot: Directory = getRootAfterEcho(
        state.root,
        state.workingDir.getAllFoldersInPath :+ filename,
        contents,
        append
      )
      if (newRoot == state.root) {
        state.setMessage(s"$filename: no such file")
      } else {
        State(newRoot, newRoot.findDescendant(state.workingDir.getAllFoldersInPath))
      }
    }

  override def apply(state: State): State =
    if (items.isEmpty) {
      state
    } else if (items.size == 1) {
      state.setMessage(items(0))
    } else {
      val op = items(items.length - 2)
      val filename = items(items.length - 1)
      val contents = createContent(items, items.length - 2)
      if (">>".equals(op)) {
        doEcho(state, contents, filename, append = true)
      } else if (">".equals(op)) {
        doEcho(state, contents, filename, append = false)
      } else {
        state.setMessage(createContent(items, items.length))
      }
    }

}
