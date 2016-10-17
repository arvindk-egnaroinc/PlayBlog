name := """Play-Blog"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.mongodb" % "mongodb-driver" % "3.2.1",
  "org.mongodb.morphia" % "morphia" % "1.1.1",
  "javax.mail" % "javax.mail-api" % "1.5.5",
  "com.sun.mail" % "javax.mail" % "1.5.5",
  "com.google.code.gson" % "gson" % "2.3",
  "com.google.code.morphia" % "morphia" % "0.101.0"
)
