/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs.commands

import com.codingmaniacs.scala.exercises.fs.State

class UnknownCommand extends Command {
  override def apply(state: State): State = state.setMessage("Command not found!")
}
