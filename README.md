# My cinema_project



# Purpose
This is a template for creating an e-cinema. 

Every user can register according to constraints for email and password, then see all available 
movies, choose a movie-session and order a ticket. 

There are specific tools for admins only to allow them adding movies, movie-sessions and 
cinemahalls.
<hr>

# Structure
* Java 11
* Maven 3.8.0
* Spring 5.2.2.RELEASE (context, orm, webmvc, securiry-core, security-config, security-web)
* Hibernate
* Jackson-databind 2.10.2
* MySQL
* Log4j 1.2.17
<hr>

# For developer

Please, configure Tomcat to start the application.

For connecting to database, please, enter your MySQL-username and password in db.properties file.

There’s one user already registered with ADMIN role (email = admin@yahoo.com, password = 1).

Application is built on REST principles. You can use Postman for testing it.

# Author

Iegor Soboliev https://github.com/IegorSoboliev 
