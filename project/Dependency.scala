import sbt._

object Dependency {
  lazy val akkaHttpVersion = "10.2.9"
  lazy val akkaVersion    = "2.6.18"

  val akkaHttp      = "com.typesafe.akka" %% "akka-http"                % akkaHttpVersion
  val akkaHttpJson  = "com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion
  val akkaActor     = "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion
  val akkaStream    = "com.typesafe.akka" %% "akka-stream"              % akkaVersion
  val logback       = "ch.qos.logback"    % "logback-classic"           % "1.2.3"

  val akkaHttpTest  = "com.typesafe.akka" %% "akka-http-testkit"        % akkaHttpVersion % Test
  val akkaActorTest = "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion     % Test
  val scalaTest     = "org.scalatest"     %% "scalatest"                % "3.1.4"         % Test
}
