# Starting The Coder’s Closet

## Get the Project

The project is divided into a frontend and backend folder, both of which must be cloned locally for development or test runs. They are separated to minimize potential issues with deployment.
Although it’s arbitrary where you store them, it’s better practice to store them in the same folder.

- set up a folder and clone each of the following into it:
    
    - `git clone https://github.com/rev-capstone/backend.git`
    - `git clone https://github.com/rev-capstone/frontend.git`
 <br>

## Development Setup

### Software Installation Requirements
*Note: indicated versions reflect what was used to build the project* 

- [JDK >= 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven >=3.8.6](https://maven.apache.org/download.cgi)
- [nodeJS >= v16.17.0](https://nodejs.org/en/download/)
- [TypeScript >= v4.8.2](https://www.typescriptlang.org/download)
- [Chrome](https://www.google.com/chrome/)  |   [Firefox](https://www.mozilla.org/en-US/firefox/new/)


<br>

### Node Package Installation

From within *frontend* run `npm install` on the command line to install all of the required node packages
<br>

### VSCode IDE addons 
If you’re using VSCode, these addons will vastly improve the coding experience (see README for links)

- Extension Pack for Java
- Spring Boot Extension Pack
- Lombok Annotations Support for VS Code
- SonarLint
- ES7+ React/Redux/React-Native snippets
- GitLens (visualize code authorship)
<br>

### Support software \[optional\]

To facilitate React development, install the *React Developer Tools* addon for Chrome or Firefox
* * *
<br>

# BACKEND CONFIG

## Database

The project is ready to run with H2 or JDBC handled databases.
Update `application.yml` with your selection of database (JPA dialect).
Here is a sample H2 configuration:

```yaml
server:
  port: 8080
spring:
  jpa:
    hibernate:
      ddl-auto: create
    database-platform: org.hibernate.dialect.H2Dialect
    defer-datasource-initialization: true
  datasource:
    url: jdbc:h2:mem:memdb
    driver-class-name: org.h2.Driver
    username: sa
    password: password
  h2:
    console.enabled: true
```
<br>

## Environment Variables

The backend JPA config in application.yml is set up to read environment variables for it’s database connection URL and credentials. If not changing from the default Postgres JDBC setup, set the following variables and their respective values:

|  Variable   |  Value   |
| --- | --- |
| DB_HOST | the url to your database |
| DB_USERNAME | database user |
| DB_PASSWORD | database password |
<br>

## SonarCloud

SonarCloud code analytics is configured in `pom.xml` with select exclusions from code coverage testing.
To use SonarCloud:
1. set up an account on [sonarcloud.io](https://sonarcloud.io) and link the project from your code host. 
2. copy the authentication token generated for you
3. set the provided token into a SONAR_TOKEN environment variable and ensure you are using a new terminal thereafter 
>new env. variables only take effect in terminals opened *after* they were set
4. In a terminal, run 
```sh
mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=rev-capstone_backend
```
where 'projectKey' is in the format :
> sonar-organization_repository-name

- "sonar-organization" is set in pom.xml
```xml
<sonar.organization>rev-capstone</sonar.organization>
```
<br>

### Sonar Config: Excluded Files
The following is the default exlusion list to omit files & folders from the Sonar coverage consideration
The format `**/name` refers to a file with a relative origin.
The format ```**/name/**``` refers to a folder and everything contained in it.

```xml
<sonar.exclusions>
	**/models/**,**/exceptions/**,**/dtos/**,**/annotations/**,**/ECommerceApplication.java,**/advice/RestExceptionHandler.java
</sonar.exclusions>
```
<hr>
<br>

## Dockerfile

Contains the Docker build instructions used for application deployment.
This project was tested only using AWS CodePipeline.
<br>

* * *

# Running the Project

## Startup

### Command Line Method

1.  open a command line to the *backend* folder where the Main file resides
2.  run: `mvn package` to compile the project
3.  if the package compiled without errors, run: `mvn spring-boot:run`
> run as `mvn spring-boot:run &` to run the server in the background and regain your CLI.  Keep in mind you'll have to terminate the process to stop the server.
4.  navigate to the *frontend* folder
5.  run `npm install` to download the required node packages
6.  run `npm start` to begin the frontent development server, which will automatically open your default browser to the base site defined

### IDE Method

1.  Open the *backend* in an IDE with capability to run projects
2.  Start/Run the project from within the IDE 
> (ex. click the play button in VSCode, or the "run" link above the main method if you have the Java Extension Pack installed.)
3.  Once the Spring server finishes booting without errors, open an IDE window to the *frontend*.
4.  Run `npm start` in the IDE terminal to start the development server
5.  If your default browser doesn’t open to the site page, manually go to `http://localhost:3000`
<br>

## Testing
The backend tests cover 33/34 outcomes in unit testing (acc. to Sonar).  Utilize these as a baseline to ensure basic functionality as you develop.
As of submission, frontend tests are not fully implemented.
### Test Backend

- Via the command line, from the within the *backend* folder run `mvn test`
- tests can also be run individually or as a suite from within an IDE

### Test Frontend

- Via the command line, from the within the *frontend* folder run `npm test`
<br>
<hr>

