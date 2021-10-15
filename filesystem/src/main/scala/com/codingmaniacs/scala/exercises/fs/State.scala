/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs

import com.codingmaniacs.scala.exercises.fs.directories.Directory

class State(val root: Directory, val workingDir: Directory, val output: String) {

  def show(): Unit = {
    println(output)
    print(State.SHELL_PROMPT)
  }

  def setMessage(message: String): State = State(root, workingDir, message)
}

object State {
  val SHELL_PROMPT = "$ "

  def apply(root: Directory, workingDir: Directory, output: String = ""): State = new State(root, workingDir, output)
}
