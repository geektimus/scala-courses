/*
 * Copyright (c) Geektimus <https://github.com/geektimus>
 */

package com.codingmaniacs.scala.exercises.fs.directories

import com.codingmaniacs.scala.exercises.fs.files.{ File, FileSystemException }

import scala.annotation.tailrec

class Directory(
  override val parentPath: String,
  override val name: String,
  val contents: List[DirEntry]
) extends DirEntry(parentPath, name) {

  def hasEntry(name: String): Boolean = findEntry(name) != null

  def getAllFoldersInPath: List[String] = path.substring(1).split(Directory.SEPARATOR).toList.filter(p => !p.isEmpty)

  def findDescendant(paths: List[String]): Directory =
    if (paths.isEmpty) this
    else findEntry(paths.head).asDirectory.findDescendant(paths.tail)

  def findDescendant(relativePath: String): Directory =
    if (relativePath.isEmpty) this
    else findDescendant(relativePath.split(Directory.SEPARATOR).toList)

  def addEntry(newEntry: DirEntry): Directory = new Directory(parentPath, name, contents :+ newEntry)

  def findEntry(entryName: String): DirEntry = {

    @tailrec
    def findEntryHelper(name: String, contentsList: List[DirEntry]): DirEntry =
      if (contentsList.isEmpty) {
        null
      } else if (contentsList.head.name.equals(name)) {
        contentsList.headOption.get
      } else findEntryHelper(name, contentsList.tail)
    findEntryHelper(entryName, contents)
  }

  def replaceEntry(entryName: String, newEntry: DirEntry): Directory =
    new Directory(parentPath, name, contents.filter(e => !e.name.equals(entryName)) :+ newEntry)

  def isRoot: Boolean = parentPath.isEmpty

  def removeEntry(entryName: String): Directory =
    if (!hasEntry(entryName)) this
    else new Directory(parentPath, name, contents.filter(x => !x.name.equals(entryName)))

  override def asDirectory: Directory = this

  override def getType: String = "Directory"

  override def asFile: File = throw new FileSystemException("A folder cannot be converted to a file!")

  override def isDirectory: Boolean = true
  override def isFile: Boolean = !isDirectory
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def empty(parentPath: String, name: String): Directory = new Directory(parentPath, name, List())

  def ROOT: Directory = Directory.empty("", "")
}
