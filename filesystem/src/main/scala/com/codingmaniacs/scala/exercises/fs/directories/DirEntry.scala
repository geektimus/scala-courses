/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs.directories

import com.codingmaniacs.scala.exercises.fs.files.File

abstract class DirEntry(val parentPath: String, val name: String) {

  def path: String = {
    val separatorIfNecessary =
      if (Directory.ROOT_PATH.equals(parentPath)) "" else Directory.SEPARATOR
    s"$parentPath$separatorIfNecessary$name"
  }

  def asDirectory: Directory

  def asFile: File

  def getType: String

  def isDirectory: Boolean
  def isFile: Boolean
}
