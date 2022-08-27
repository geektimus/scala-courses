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
      val log4j2 = "2.17.2"
      val scalaLogging = "3.9.5"
      val slf4j = "2.17.2"
      val scalaCheck = "1.16.0"
      val scalaTest = "3.2.12"
      val scalatic = "3.2.12"
      val scalaTestPlus = "3.2.12.0"
    }

    val log4j2Api    = "org.apache.logging.log4j"   %  "log4j-api"        % Version.log4j2
    val log4j2Core   = "org.apache.logging.log4j"   %  "log4j-core"       % Version.log4j2 % Runtime
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging"    % Version.scalaLogging
    val slf4j        = "org.apache.logging.log4j"   %  "log4j-slf4j-impl" % Version.slf4j
    val scalaCheck   = "org.scalacheck"             %% "scalacheck"       % Version.scalaCheck
    val scalaTest    = "org.scalatest"              %% "scalatest"        % Version.scalaTest
    val scalatic     = "org.scalactic"              %% "scalactic"        % Version.scalatic

    val scalaTestPlusCheck = "org.scalatestplus" %% "scalacheck-1-16" % Version.scalaTestPlus
    val scalaTestPlusMock = "org.scalatestplus" %% "mockito-4-5" % Version.scalaTestPlus

  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings = commonSettings ++ scalafmtSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "3.1.3",
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
