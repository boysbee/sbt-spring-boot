name:="sbt-spring-boot"

version :="1.0"

exportJars := true

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

libraryDependencies ++= Seq(
    "org.springframework.boot" % "spring-boot-starter"  % "1.0.1.RELEASE"
)

