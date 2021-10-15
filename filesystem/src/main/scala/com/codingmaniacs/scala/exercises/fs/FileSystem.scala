/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs

import java.util.Scanner

import com.codingmaniacs.scala.exercises.fs.commands.Command
import com.codingmaniacs.scala.exercises.fs.directories.Directory

object FileSystem {

  def main(args: Array[String]): Unit = {

    val root = Directory.ROOT
    var state = State(root, root)

    val scanner = new Scanner(System.in)

    while (true) {
      state.show()
      state = Command.from(scanner.nextLine()).apply(state)
    }
  }

}
