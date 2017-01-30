lazy val Name = "resty-seed"
lazy val Version = "0.0.1"
lazy val ScalaVersion = "2.12.1"
lazy val JettyVersion = "9.3.9.v20160517"

lazy val project = Project(Name, file("."))
  .settings(
    name := "resty-seed",
    scalaVersion := "2.12.0",
    libraryDependencies ++= Seq(
      "com.github.takezoe"  %% "resty"             % "0.0.7",
      "org.eclipse.jetty"   %  "jetty-webapp"      % JettyVersion % "container",
      "org.eclipse.jetty"   %  "jetty-plus"        % JettyVersion % "container",
      "org.eclipse.jetty"   %  "jetty-annotations" % JettyVersion % "container",
      "javax.servlet"       %  "javax.servlet-api" % "3.0.1" % "provided",
      "org.scalikejdbc"     %% "scalikejdbc"       % "2.5.0",
      "com.h2database"      %  "h2"                % "1.4.193",
      "ch.qos.logback"      %  "logback-classic"   % "1.1.7"
    ),
    scalacOptions := Seq("-deprecation"),
    javaOptions in Jetty ++= Seq(
      "-Xdebug",
      "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000"
    )
  )
  .enablePlugins(JettyPlugin)

