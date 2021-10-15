/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs.commands

import com.codingmaniacs.scala.exercises.fs.State
import com.codingmaniacs.scala.exercises.fs.directories.{ DirEntry, Directory }

class MkDir(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry = Directory.empty(state.workingDir.path, name)
}
