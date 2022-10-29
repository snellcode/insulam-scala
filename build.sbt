ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"

lazy val root = (project in file("."))
  .settings(
    name := "insulam"
  )

resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies += "com.github.fabio-t" % "terrain-generator" % "0.1.2"