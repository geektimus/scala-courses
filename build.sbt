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

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {

    object Version {
      val log4j2 = "2.24.1"
      val scalaLogging = "3.9.5"
      val scalaCheck = "1.17.1"
      val scalaTest = "3.2.18"
      val scalaTestPlus = "3.2.19.0"
    }

    val log4j2Api    = "org.apache.logging.log4j"   %  "log4j-api"        % Version.log4j2
    val log4j2Core   = "org.apache.logging.log4j"   %  "log4j-core"       % Version.log4j2 % Runtime
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging"    % Version.scalaLogging
    val slf4j        = "org.apache.logging.log4j"   %  "log4j-slf4j-impl" % Version.log4j2
    val scalaCheck   = "org.scalacheck"             %% "scalacheck"       % Version.scalaCheck
    val scalaTest    = "org.scalatest"              %% "scalatest"        % Version.scalaTest
    val scalatic     = "org.scalactic"              %% "scalactic"        % Version.scalaTest

    val scalaTestPlusCheck = "org.scalatestplus" %% "scalacheck-1-18" % Version.scalaTestPlus
    val scalaTestPlusMock = "org.scalatestplus" %% "mockito-5-12" % Version.scalaTestPlus

  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings = commonSettings ++ scalafmtSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "3.5.1",
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
