/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs.commands

import com.codingmaniacs.scala.exercises.fs.State

class Cat(filename: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.workingDir
    val dirEntry = wd.findEntry(filename)
    if (dirEntry == null || !dirEntry.isFile) {
      state.setMessage(s"$filename: No such file")
    } else {
      state.setMessage(dirEntry.asFile.contents)
    }
  }

}
