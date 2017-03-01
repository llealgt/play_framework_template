# Play Flamework Template
My personal template to create web applications and REST API's using [Play framework 2.5(based on Java 8)](https://www.playframework.com/).
It is designed to be my personal template(and give me some remainders embedded in this readme)for creating new appliations but if anybody finds it useful, feel free to use.

After creating some applications in play,i noticed the basic setup consumed time and it was repeated over and over between different applications.
I'm an enemy of doing the same job more than 1 time(if you do it twice, you can automate it).
In this template i compiled some of the basic setup and common shared configurations , so when creating a new application i can 
get on coding as fast as posible instead of repeating the same basic configurations. 

Basic useful feature list in this template:

 * Already contains a build.sbt file with the common configurations i use, like library dependencies ,MySQL connectors, eclipse enabling.
 * Already contains an application.conf with allowed hosts example(i always forgot to add it ,and its syntax), DB connection configuration example, application secret example(if the application secret is not set, it will not run in production mode), jpa Enabling
 * Already contains a template for the persistence.xml configuration file
 * Contains an example routes file  as a remainder of the syntax of the routes configuration(how URLs are routed to controllers that serve the HTTP requests). This example is based on an fake "Promotions" database.
 * It contains a Promotion model, and a Promotion controller to serve as example of how the routes file calls a controller method, and the controller uses the model to acces database.The controller then returns a json representation of the promotions(useful for REST API). This model and controller can be deleted.
 * It already contains the configuration to be able to code in Eclipse or Netbeans.


Altough the idea is to have everything ready to start coding, there are some configurations which are specific for every new application and still need to be performed on every new application. This is the checklist of tasks to perform when creating a new application:

 * Make sure to have JDK 8 and JAVA_HOME variable pointing to it.
 * Open the hidden file .project and add the application name to the "name" tags
 * Open the build.sbt file and:
 	* change the application name
 	* The template  already contains the line :libraryDependencies += filters, but in case it is not there, add it(used to filter which hosts or domain are allowed to connect)
 	* The template already contains the following 2 lines,they are used to indicate that no API documentation is desired on the generated distribution package. Make sure they are there, or if you want documentation in your production distribution package remove them:
 		```
    	sources in (Compile, doc) := Seq.empty
    	publishArtifact in (Compile, packageDoc) := false
    	```
 	*  Make sure the followin line exists if you are using jpa: 		
 	PlayKeys.externalizeResources := false
  * Open the conf/application.conf file and:
  	* Configure the neccesary database connections.The template contains an example for a connection  to a fake database, the connection is called "library" , replace db.library  to the name that will be given, for example db.invoices ,and replace host:port ,as well as user and password.
  	* Give a name to a jndi connection in the line db.<name>.jndiName=<the name>,  the value given in <the name> will be used in the persistence.xml file
  	* Configure which hosts and domains can connect to the application. The template already contains an example, if it is a public application use allowed = [.]
  * Open conf/META-INF/persistence.xml file and configure the connection name, with the same name used in <the name> in the jndiName line of application.conf in the tags  non-jta-data-source
 	
  With this configuration, the application should run correctly in development mode. To start the application in development mode , just execute: sbt run

## Deployment in Production

To deploy the application in production, it is not needed a web server like apache, only the JDK installed in the server ,an "application secret" and generate a distribution package. The instructions are:
* Create an "application secret"(can be manually created ,or generated with the utility : sbt playGenerateSecret).
* Create a new environment variable and assign it the application secret, in this template the variable $application_secret_environment_variable is used(it can be added to the .bashrc file: export application_secret_environment_variable="test").
* Go to the conf/application.conf file and add a reference to the variable created(for the default example in the template, add : play.crypto.secret=${?application_secret_environment_variable}
* Generate a distribution package executing: sbt run
. This will generate a zip file with the binaries to run the application in the target/universal directory ( the name of the zip is the same as the name of the application)
* Copy the .zip file to the server in which the application will run and unzip it in a desired directory, then go to the bin directory. If it is a Linux server(as in my case) ,give the executable file permissions with chmod +x <application_name> and then , lust launch the <application_name> executable.
* The application should be running and a log directory will be generated to monitoring.
