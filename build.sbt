val AkkaVersion = "2.6.18"
val AkkaHttpVersion = "10.2.6"
val SprayJsonVersion = "10.2.6"
lazy val root = (project in file("."))
  .settings(
    name := "UserApi",
      libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
        "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
        "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
        "com.typesafe.akka" %% "akka-http-spray-json" % SprayJsonVersion,
        "io.spray" %%  "spray-json" % "1.3.6"
      )
  )
