ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.15"

lazy val root = (project in file("."))
  .settings(
    name := "fp-simplified-scala"
  )

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.9.0",
  "org.typelevel" %% "cats-effect" % "3.5.7"
)

addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")

scalacOptions += "-Wnonunit-statement"
