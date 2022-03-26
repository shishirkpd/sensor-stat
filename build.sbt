import Dependency._

fork := true

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.skp",
      scalaVersion    := "2.13.4"
    )),
    name := "sensor-stats",
    libraryDependencies ++= Seq(
      akkaHttp,
      akkaHttpJson,
      akkaActor,
      akkaStream,
      logback,
      akkaHttpTest,
      akkaActorTest,
      scalaTest
    ),
    Compile / packageBin / mainClass := Some("com.skp.MainApp")
  )