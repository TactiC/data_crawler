name := "data-crawler"

version := "0.1"

scalaVersion := "2.12.4"

organization    := "nl.ronalddehaan"
scalaVersion    := "2.12.4"

libraryDependencies ++= {
  lazy val akkaHttpVersion = "10.0.11"
  lazy val akkaVersion    = "2.5.8"
  Seq (
    "com.typesafe.akka" %% "akka-http"                % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-xml"            % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream"              % akkaVersion,
//    "org.jsoup"         %  "jsoup"                    % "1.11.2",
    "net.ruippeixotog"  %% "scala-scraper"            % "2.0.0",

    "com.typesafe.akka" %% "akka-http-testkit"        % akkaHttpVersion   % Test,
    "com.typesafe.akka" %% "akka-testkit"             % akkaVersion       % Test,
    "com.typesafe.akka" %% "akka-stream-testkit"      % akkaVersion       % Test,
    "org.scalatest"     %% "scalatest"                % "3.0.1"           % Test
  )
}

