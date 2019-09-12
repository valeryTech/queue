organization in ThisBuild := "ru.servplus"
version in ThisBuild := "1.0-SNAPSHOT"

lagomCassandraEnabled in ThisBuild := false
lagomKafkaEnabled in ThisBuild := false

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.8"
scalacOptions += "-Ymacro-debug-lite"

def defaultSettings = Seq(
  /*
   * Репозитории
   */
  credentials += Credentials("Artifactory Realm", "nexus312", "developer", "APmGuQuvMfLormw" /*alqzwx*/),
  resolvers += "Artifactory-snapshot" at "http://nexus312:8080/artifactory/ext-snapshot-local/",
  resolvers += "Artifactory-release" at "http://nexus312:8080/artifactory/ext-release-local/",
  resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
  resolvers += Resolver.bintrayRepo("hajile", "maven"),
)

def defaultBundleSettings = Seq(
  javaOptions in Universal := Seq(
    "-J-Xmx128m",
    "-J-Xms128m"
  ),
  dockerRepository := Some("docker-repo.servplus.ru:5000")
)

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `queue` = (project in file("."))
  .settings(defaultSettings)
  .aggregate(`queue-api`, `queue-impl`)

lazy val `queue-api` = (project in file("queue-api"))
  .enablePlugins(LagomOpenApiPlugin)
  .settings(defaultSettings)
  .settings(
    organization := "ru.servplus.api.queue.generate",
    libraryDependencies ++= Seq(
      lagomScaladslApi
    ),
    target in (Compile, lagomOpenAPIGenerateDescriptor) := sourceManaged.value / "main"
  )

lazy val `queue-impl` = (project in file("queue-impl"))
  .enablePlugins(LagomScala)
  .settings(defaultSettings)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`queue-api`)