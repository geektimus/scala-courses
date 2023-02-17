/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs.commands

import com.codingmaniacs.scala.exercises.fs.State
import com.codingmaniacs.scala.exercises.fs.directories.DirEntry

class Ls extends Command {

  override def apply(state: State): State = {
    val contents = state.workingDir.contents
    val outputString = createOutputString(contents)
    state.setMessage(outputString)
  }

  def createOutputString(contents: List[DirEntry]): String =
    if (contents.isEmpty) ""
    else {
      val entry = contents.head
      s"${entry.name} [${entry.getType}]\n${createOutputString(contents.tail)}"
    }

}
