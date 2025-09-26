# 4156-Miniproject-2025-Students

This is the Github repo for the Individual Project associated with COMS 4156 Advanced Software Engineering. This project implements a RESTful API for a library management system. 

## Building and Running a Local Instance

In order to build and use our service you must install the following:

**Maven 3.9.11**: https://maven.apache.org/download.cgi Download and follow the installation instructions

**JDK 17**: We recommend you use https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

**IntelliJ IDE**: We recommend using IntelliJ but you are free to use any IDE of your choice https://www.jetbrains.com/idea/download/?section=mac

When you open IntelliJ you have the option to clone from a GitHub repo, click the green code button and copy the http line that is provided there and give it to your IDE to clone.

### Build the Project

Run `mvn clean package`

## Running Tests

Our unit tests are located under the directory 'src/test'. To run our project's tests in IntelliJ using Java 17, you must first build the project.

To run all tests from the command line: `mvn clean test`    

To generate code coverage reports: `mvn jacoco:report`

## Endpoints

This section describes the endpoints that this service provides, with their inputs and outputs. 

### GET /
**Expected Input Parameters**: N/A
**Expected Output**: A String containing a welcome message
Returns a welcome message for the API
**Upon Success**: HTTP 200 Status Code is returned along with "Welcome to the home page! In order to make an API call direct your browser or Postman to an endpoint." in the response body.

### GET /index
**Expected Input Parameters**: N/A
**Expected Output**: A String containing a welcome message
Alternative welcome page endpoint
**Upon Success**: HTTP 200 Status Code is returned along with the same welcome message as GET /. 

### GET /book/{id}
**Expected Input Parameters**: id (int) - The unique identifier of the book to retrieve
**Expected Output**: A Book object or error message 
Returns the details of the specified book.
**Upon Success**: HTTP 200 Status Code is returned along with the matching Book object in the response body.
**Upon Failure**: HTTP 404 Status Code is returned along with "Book not found." in the response body

### PUT /books/available 
**Expected Input Parameters**: N/A
**Expected Output**: A list of Book objects that have available copies
Get and return a list of all the books with available copies.
**Upon Success**: HTTP 200 Status Code is returned along with a list of available Book objects in the response body.
**Upon Failure**: HTTP 500 Status Code is returned along with "Error occurred when getting all available books" in the response body


### PATCH /book/{bookId}/add
**Expected Input Parameters**: bookId (Integer) - The unique id of the book to add a copy to
**Expected Output**: The updated Book object or error message
Adds a copy to the Book object if it exists.
**Upon Success**: HTTP 200 Status Code is returned along with the updated Book object in the response body.
**Upon Failure**: 
- HTTP 404 Status Code with "Book not found." in the body if the specified book does not exist.
- HTTP 500 Status Code with "Error occurred when adding a copy to the book" in the response body.

### GET /books/recommendation
**Expected Input Parameters**: N/A
**Expected Output**: A list of 10 recommended Book objects
Get and return a list of 10 recommended books. Half are the most popular books (highest checkout count). Half are randomly selected books.
**Upon Success**: HTTP 200 Status Code is returned along with a list of 10 recommended Book objects in the response body.
**Upon Failure**: 
- HTTP 400 Status Code with "Not enough recommendedbooks" in the body if there are fewer than 10 books available.
- HTTP 500 Status Code with "Error occurred when getting recommendations" in the response body.

### PATCH /checkout
**Expected Input Parameters**: id (Integer) - The ID of the book to check out
**Expected Output**: The updated Book object or error message
Check out a book by its ID. Updates all relevant fields within the corresponding Book object to indicate it has been checked out.
**Upon Success**: HTTP 200 Status Code is returned along with the updated Book object in the response body.
**Upon Failure**:
- HTTP 400 Status Code with "Cannot checkout book" in the body if no copies are available for checkout.
- HTTP 404 Status Code with "Book not found" in the body if the specified book does not exist.
- HTTP 500 Status Code with "Error occurred when checking out book" in the response body.

## Style Checking Report

We used the tool "checkstyle" to check the style of our code and generate style checking reports. Here is the report as of the day of submission:

**Command to run**: `mvn checkstyle:check`
**Status**: 0 Checkstyle violations
**Report generation**: `mvn checkstyle:checkstyle`
**Report location**: `target/site/checkstyle.html`

## Branch Coverage Reporting

We used JaCoCo to perform branch analysis in order to see the branch coverage of the relevant code within the code base.

**Command to generate report**: `mvn jacoco:report`
**Report location**: `target/site/jacoco/index.html`
**Current branch coverage**: 90%

## Static Code Analysis

We used PMD to perform static analysis on our codebase.

**Maven command**: `mvn pmd:check`

**Manual installation and run**:
```bash
cd $HOME
curl -OL https://github.com/pmd/pmd/releases/download/pmd_releases%2F7.17.0/pmd-dist-7.17.0-bin.zip
unzip pmd-dist-7.17.0-bin.zip
alias pmd="$HOME/pmd-bin-7.17.0/bin/pmd"
pmd check -d /path/to/your/project -R rulesets/java/quickstart.xml -f text
```

## Continuous Integration Report

This repository uses GitHub Actions to perform continuous integration. The CI pipeline compiles the program and runs all unit tests on pushes to and pull requests targeting the main branch.

**Workflow file**: `.github/workflows/ci.yml`
**Actions performed**:
- Maven Package: `mvn -B clean package -DskipTests`
- Maven Verify: `mvn -B clean verify`
- Run Checkstyle: `mvn checkstyle:check`
- Generate JaCoCo Report: `mvn jacoco:report`

## Tools used

This section includes notes on tools used in building this project

### Maven Package Manager
Used for dependency management and build automation.

### GitHub Actions CI
This is enabled via the "Actions" tab on GitHub.
Currently, this runs a Maven build to make sure the code builds and all tests pass on the main branch.

### Checkstyle
We use Checkstyle for code style reporting. Note that Checkstyle does NOT get run as part of the CI pipeline.
For running Checkstyle manually, you can use the "Checkstyle-IDEA" plugin for IntelliJ.

### PMD
We are using PMD to do static analysis of our Java code.

### JUnit
JUnit tests get run automatically as part of the CI pipeline.

### JaCoCo
We use JaCoCo for generating code coverage reports.

### Spring Boot
We used Spring Boot framework for building the REST API.

### Mockito
We used Mockito for mocking in our unit tests.

### Mock Data Storage
Our service uses mock data stored in `src/main/resources/mockdata/books.json` which contains a collection of books with various properties for testing and demonstration purposes.