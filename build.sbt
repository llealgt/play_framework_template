name := "PlayFrameworkTemplate"
organization := "com.devongt"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.8"

libraryDependencies += filters
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"
libraryDependencies += javaJpa
libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"
// Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
EclipseKeys.preTasks := Seq(compile in Compile)

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           // Java project. Don't expect Scala IDE
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)  // Use .class files instead of generated .scala files for views and routes

// The following 2 lines are used to indicate that no API documentation is desired in the distribution artificat of production mode
sources in (Compile, doc) := Seq.empty

publishArtifact in (Compile, packageDoc) := false

// The next line is necesary in production mode only if JPA is being used.
PlayKeys.externalizeResources := false
