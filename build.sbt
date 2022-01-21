name := "PIM_Solutions"

version := "1.0"

scalaVersion := "2.13.7"

scalacOptions ++= Seq(
  "-language:implicitConversions",
  "-language:higherKinds",
  "-Wnumeric-widen"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"