lazy val `scala-courses` =
  project
    .in(file("."))
    .enablePlugins(AutomateHeaderPlugin, GitVersioning, JavaAppPackaging, AshScriptPlugin)
    .settings(name := "Scala Courses")
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
          library.log4j2Api,
          library.log4j2Core,
          library.log4j2Scala,
          library.scalaLogging,
          library.slf4j,
          library.scalaCheck % Test,
          library.specs2 % Test,
          library.specs2Mock % Test,
          library.scalaTest % Test,
        library.scalaTestPlus % Test
        )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {

    object Version {
      val log4j2       = "2.14.1"
      val log4j2Scala  = "12.0"
      val scalaLogging = "3.9.4"
      val slf4j        = "2.14.1"
      val scalaCheck   = "1.15.4"
      val specs2       = "4.12.3"
      val scalaTest    = "3.2.9"
    }

    val log4j2Api    = "org.apache.logging.log4j"   %  "log4j-api"        % Version.log4j2
    val log4j2Core   = "org.apache.logging.log4j"   %  "log4j-core"       % Version.log4j2 % Runtime
    val log4j2Scala  = "org.apache.logging.log4j"   %% "log4j-api-scala"  % Version.log4j2Scala
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging"    % Version.scalaLogging
    val slf4j        = "org.apache.logging.log4j"   %  "log4j-slf4j-impl" % Version.slf4j
    val scalaCheck   = "org.scalacheck"             %% "scalacheck"       % Version.scalaCheck
    val specs2       = "org.specs2"                 %% "specs2-core"      % Version.specs2
    val specs2Mock   = "org.specs2"                 %% "specs2-mock"      % Version.specs2
    val scalaTest    = "org.scalatest"              %% "scalatest"        % Version.scalaTest
    val scalaTestPlus = "org.scalatestplus"         %% "scalacheck-1-15"  % "3.2.9.0"

  }


lazy val compilerPlugins =
  new {
    val kindProjector = "org.typelevel"     %% "kind-projector"  % "0.10.3" cross CrossVersion.binary
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
  gitSettings ++
  scalafmtSettings

lazy val commonSettings =
  Seq(
    // scalaVersion from .travis.yml via sbt-travisci
    // scalaVersion := "2.12.9",
    version := "0.1.0-SNAPSHOT",
    organization := "com.codingmaniacs.scala.courses",
    headerLicense := Some(HeaderLicense.Custom("Copyright (c) Geektimus <https://github.com/geektimus>")),
    scalacOptions ++= Seq(
        "-deprecation",
        "-encoding",
        "UTF-8",
        "-feature",
        "-language:existentials",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-language:postfixOps",
        "-target:jvm-1.8",
        "-unchecked",
        "-Xfatal-warnings",
        "-Xlint",
        "-Ywarn-dead-code",
        "-Ywarn-numeric-widen",
        "-Ywarn-unused",
        "-Ywarn-unused:imports",
        "-Ywarn-value-discard"
      ),
    Test / parallelExecution := false
  )

lazy val gitSettings =
  Seq(
    git.useGitDescribe := true
  )

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true
  )
