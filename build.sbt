ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "magnolia-annotations-bug"
  )
  .aggregate(tapir, generic, baseclass)

lazy val tapir = (project in file("tapir"))
  .settings(
    name := "tapir",
    libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-core" % "0.20.0-M9"
  )

lazy val generic = (project in file("generic"))
  .settings(
    name := "generic",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
  )

lazy val baseclass = (project in file("baseclass"))
  .settings(
    name := "baseclass"
  )
  .dependsOn(generic)

lazy val names = (project in file("names"))
  .settings(
    name := "names",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
  )

lazy val `pattern-matching` = (project in file("pattern-matching"))
  .settings(
    name := "pattern-matching",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
  )
