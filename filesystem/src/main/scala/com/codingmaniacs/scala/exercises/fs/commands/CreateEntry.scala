/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs.commands

import com.codingmaniacs.scala.exercises.fs.State
import com.codingmaniacs.scala.exercises.fs.directories.{ DirEntry, Directory }

abstract class CreateEntry(name: String) extends Command {

  def updateStructure(currentDir: Directory, paths: List[String], newEntry: DirEntry): Directory =
    if (paths.isEmpty) {
      currentDir.addEntry(newEntry)
    } else {
      val oldEntry = currentDir.findEntry(paths.head).asDirectory
      currentDir.replaceEntry(oldEntry.name, updateStructure(oldEntry, paths.tail, newEntry))
    }

  override def apply(state: State): State = {
    val wd = state.workingDir
    if (wd.hasEntry(name)) {
      state.setMessage(s"$name already exists")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(s"$name must not contain separators")
    } else if (checkIllegal(name)) {
      state.setMessage(s"$name: illegal entry name!")
    } else {
      doCreateEntry(state)
    }
  }

  def checkIllegal(name: String): Boolean = name.contains(".")

  def doCreateEntry(state: State): State = {
    val wd = state.workingDir

    val allDirsInPath = wd.getAllFoldersInPath

    val newEntry = createSpecificEntry(state)

    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)

    val newWD = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWD)

  }

  def createSpecificEntry(state: State): DirEntry
}
