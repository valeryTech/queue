resolvers += Resolver.sonatypeRepo("public")
resolvers += MavenRepository("Artifactory-release", "http://nexus312:8080/artifactory/ext-release-local/")
resolvers += MavenRepository("Artifactory-snapshot", "http://nexus312:8080/artifactory/ext-snapshot-local/")

// The Lagom plugin
addSbtPlugin("com.lightbend.lagom" % "lagom-sbt-plugin" % "1.5.1")
// Need for generating API from OpenAPI specification
addSbtPlugin("ru.servplus.sbt"         % "lagom-descriptor-generator-sbt-plugin" % "0.2.6")
addSbtPlugin("io.get-coursier"         % "sbt-coursier"                          % "1.0.3")
addSbtPlugin("net.virtual-void"        % "sbt-dependency-graph"                  % "0.9.2")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.14")
