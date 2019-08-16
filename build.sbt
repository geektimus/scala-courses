lazy val `scala-courses` =
  project
    .in(file("."))
    .enablePlugins(AutomateHeaderPlugin)
    .settings(name := "Scala Courses")
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.log4j2Api,
        library.log4j2Core,
        library.scalaLogging,
        library.slf4j,
        library.scalaCheck % Test,
        library.scalaTest % Test,
        library.scalatic % Test,
        library.scalaTestPlusCheck % Test,
        library.scalaTestPlusMock % Test
      )
    )
  .aggregate(`file-system-exercise`)

lazy val `file-system-exercise` =
  project
    .in(file("filesystem"))
    .enablePlugins(AutomateHeaderPlugin, GitVersioning, JavaAppPackaging, AshScriptPlugin)
    .settings(name := "Virtual Filesystem (Exercise)")
    .settings(settings)
    .settings(dockerSettings)
    .settings(
      libraryDependencies ++= Seq(
        library.log4j2Api,
        library.log4j2Core,
        library.log4j2Scala,
        library.scalaLogging,
        library.slf4j,
        library.scalaCheck % Test,
        library.specs2 % Test
      ),
      version in Docker := "0.1.0-SNAPSHOT"
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {

    object Version {
      val log4j2 = "2.19.0"
      val scalaLogging = "3.9.5"
      val scalaCheck = "1.17.0"
      val scalaTest = "3.2.15"
      val scalaTestPlus = "3.2.15.0"
    }

    val log4j2Api    = "org.apache.logging.log4j"   %  "log4j-api"        % Version.log4j2
    val log4j2Core   = "org.apache.logging.log4j"   %  "log4j-core"       % Version.log4j2 % Runtime
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging"    % Version.scalaLogging
    val slf4j        = "org.apache.logging.log4j"   %  "log4j-slf4j-impl" % Version.log4j2
    val scalaCheck   = "org.scalacheck"             %% "scalacheck"       % Version.scalaCheck
    val scalaTest    = "org.scalatest"              %% "scalatest"        % Version.scalaTest
    val scalatic     = "org.scalactic"              %% "scalactic"        % Version.scalaTest

    val scalaTestPlusCheck = "org.scalatestplus" %% "scalacheck-1-17" % Version.scalaTestPlus
    val scalaTestPlusMock = "org.scalatestplus" %% "mockito-4-6" % Version.scalaTestPlus

  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings = commonSettings ++ scalafmtSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "3.2.2",
    version := "0.2.0-SNAPSHOT",
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
      "-unchecked",
      "-Xfatal-warnings"
    ),
    Test / parallelExecution := false
  )

lazy val scalafmtSettings = Seq(scalafmtOnCompile := true)
