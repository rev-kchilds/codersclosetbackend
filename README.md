# Coder’s Closet  

An e-commerce storefront made in fulfillment of Project 3’s requirements.

The application covers both backend (*Java, Spring*) and frontend (*Node, React, MaterialUI*).  Each resides in its own repository.  They are separated to minimize potential issues with deployment.  See the **STARTUP document** for more information and the recommended setup procedures.

Starting from a barebones Spring/React/MaterialUI framework provided by Revature, the application was modified to include an additional 6 pre-selected features and 70% testing coverage to reach MVP, as well as some additional customizations.  See the respective section below for more details.
* * *
<br>

## App Features
### MVP
- Dark Mode
- Input Validation
- Quantity Select
- Remove Items From Cart
- Search For Products
- UI/UX Error Handling
- 70% Unit Test Coverage
### Additional Work
- "Featured Products" slideshow on main product page
- graphical overhaul
- 95.9% JUnit unit test coverage
- Passed SonarCloud quality gate
* * *
<br>

## Technologies Involved
- Java 8SE
- Spring w/ Boot, MVC, Data
- Maven
- ModelMapper
- JUnit5 & Mockito
- NodeJS
- React
- MaterialUI
- Jest
- SonarCloud
<hr>
<br>

## Prerequisites For Development

- an IDE (ex. VSCode)
- a modern browser
- JDK 11
- maven
- git
- nodejs
* * *
<br>

## Environment Variables
The backend JPA config in application.yml is set up to read environment variables for it’s database connection URL and credentials. If not changing from the default Postgres JDBC setup, set the following variables and their respective values:

|  Variable   |  Value   |
| --- | --- |
| DB_HOST | the url to your database |
| DB_USERNAME | database user |
| DB_PASSWORD | database password |
* * *
<br>

## Development Setup
> *See **STARTUP** document*
<hr>
<br>

## Where do I download X?

**JDK 11.0.16**
https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html

**Maven**
https://maven.apache.org/download.cgi

**ModelMapper**
Used for Model <-> DTO conversion
http://modelmapper.org/getting-started/

**Node.js**
https://nodejs.org/en/download/

**Typescript**
It’s included in the project build, but if you need to download it independently:
https://www.typescriptlang.org/download

**Git**
https://git-scm.com/downloads

### Beneficial VSCode Addons

- [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack)
- [SonarLint](https://marketplace.visualstudio.com/items?itemName=SonarSource.sonarlint-vscode&ssr=false)
- [ES7+ React/Redux/React-Native snippets](https://marketplace.visualstudio.com/items?itemName=dsznajder.es7-react-js-snippets)
- [ESLint](https://marketplace.visualstudio.com/items?itemName=dbaeumer.vscode-eslint)
- [GitLens](https://marketplace.visualstudio.com/items?itemName=eamodio.gitlens)
<br>

### Beneficial Browser Addons

**React Developer Toolkit**
- [Firefox](https://addons.mozilla.org/en-US/firefox/addon/react-devtools/)
- [Chrome](https://chrome.google.com/webstore/detail/react-developer-tools/fmkadmapgofadopljbjfkapdkoienihi?hl=en)

* * *