sbt-spring-boot
---------------
sample application with spring-boot create by sbt and use one-jar plugin for build fat jar.

Requirements
-----------
- sbt 0.13.0+

Usage
----------
`sbt run`

* 1 for java style
* 2 for scala style

Package to jar
----------
`sbt one-jar`

Run with jar
----------
`java -jar target/scala-<scala-version>/sbt-spring-boot_<scala-version>-<app-version>-one-jar`
