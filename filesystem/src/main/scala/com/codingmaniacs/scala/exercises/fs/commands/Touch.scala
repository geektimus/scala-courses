/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs.commands

import com.codingmaniacs.scala.exercises.fs.State
import com.codingmaniacs.scala.exercises.fs.directories.DirEntry
import com.codingmaniacs.scala.exercises.fs.files.File

class Touch(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry = File.empty(state.workingDir.path, name)
}
