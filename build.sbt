name:="sbt-spring-boot"

version :="1.0"

exportJars := true

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

// java
mainClass := Some("my.sample.SpringApplication")
// scala
// mainClass := Some("my.sample.ScalaSpringBootApplication")


libraryDependencies ++= Seq(
    "org.springframework.boot" % "spring-boot-starter"  % "1.0.1.RELEASE"
)

