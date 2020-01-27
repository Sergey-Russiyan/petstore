# Getting started with REST API testing with Serenity and Cucumber 4

This tutorial show you how to get started with REST-API testing using Serenity and Cucumber 4. 

## Get the code

Git:

Or simply [download a zip](https://github.com/) file.

## The starter project
The best place to start with Serenity and Cucumber is to clone or download the starter project on Github ([https://github.com/](https://github.com/)). 
This project gives you a basic project setup, along with some sample tests and supporting classes. 
The project comes with some RestAssured-based tests. 
The project also illustrates how you might use...

### The project directory structure
The project has build scripts for both Maven and Gradle, and follows the standard directory structure used in most Serenity projects:
```Gherkin
src
  + main
  + test
    + java                                Test runners and supporting code
    + resources
      + features                          Feature files 
          + status
          + trades
             record_a_new_trade.feature 
      + templates                         Freemarker templates and properties files                

```

### Adding the Cucumber 4 dependency
Serenity seamlessly supports both Cucumber 2.x and Cucumber 4. 
However, this flexibility requires a little tweaking in the build dependencies.

If you are using Maven, you need to do the following:
- exclude the default `cucumber-core` dependency from your `serenity-core` dependency
- Replace your `serenity-cucumber` dependency with the `serenity-cucumber4` dependency
- Add dependencies on the Cucumber 4.x version of `cucumber-java` and `cucumber-junit` into your project

An example of the correctly configured dependencies is shown below:
```xml
<dependency>
    <groupId>net.serenity-bdd</groupId>
    <artifactId>serenity-core</artifactId>
    <version>2.0.38</version>
    <scope>test</scope>
    <exclusions>
        <exclusion>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>net.serenity-bdd</groupId>
    <artifactId>serenity-cucumber4</artifactId>
    <version>1.0.4</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>4.2.0</version>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit</artifactId>
    <version>4.2.0</version>
</dependency>
```

If you are using Gradle, you need to ensure that the 4.x version of `cucumber-core` is used using the _resolutionStrategy_ element, and also add the Cucumber 4.x version of `cucumber-java` and `cucumber-junit` dependencies as mentioned above:
```Gradle
configurations.all {
    resolutionStrategy {
        force "io.cucumber:cucumber-core:4.2.0"
    }
}

dependencies {
    testCompile "net.serenity-bdd:serenity-core:2.0.38",
                "net.serenity-bdd:serenity-cucumber4:1.0.4",
                "io.cucumber:cucumber-core:4.2.0",
                "io.cucumber:cucumber-junit:4.2.0"
}
```

In the rest of this article, we will walk through some of the highlights of both versions. Let’s start 
off with the version on the master branch, 
which uses lightweight page objects and actions.

## A simple GET scenario
The project comes with few simple scenarios, 

 scenario exercises the `/api/` endpoint
Both variations of the sample project uses the sample Cucumber scenario. 

```Gherkin
 Feature: Check on inventory status
 
  The `/store/inventory` end point returns inventory .
 
   Scenario: Application inventory end-point
     Given the application is running
     When I check the store inventory
     Then the API should return valid JSON

```

The glue code for this scenario illustrates the layered approach we find works well for both web and non-web acceptance tests.
The glue code is responsible for orchestrating calls to a layer of more business-focused classes, which perform the actual REST calls.

```java
    @Steps
    ApplicationStatus theApplication;

    @Given("the application is running")
    public void the_application_is_running() {
        assertThat(theApplication.currentStatus()).isEqualTo(RUNNING);
    }

    @When("I check the application status")
    public void i_check_the_application_status() {
        theApplication.readStatusMessage();
    }
```

The actual REST calls are performed using RestAssured in the action classes, like `ApplicationStatus` here. 
These use either RestAssured (if we don't want the queries to appear in the reports) or SerenityRest (if we do):

```java
public class ApplicationStatus {

    public AppStatus currentStatus() {
        int statusCode = RestAssured.get(STATUS.getUrl()).statusCode();
        return (statusCode == 200) ? AppStatus.RUNNING : AppStatus.DOWN;
    }

    @Step("Get current status message")
    public void readStatusMessage() {
        SerenityRest.get(STATUS.getUrl());
    }
}
```

In steps that perform assertions, we can also use the `SerenityRest.restAssuredThat()` helper method, 
which lets us make a RestAssured assertion on the last response the server sent us:

```java

    @Then("the API should return {string}")
    public void the_API_should_return(String expectedMessage) {
        restAssuredThat(lastResponse -> lastResponse.body(equalTo(expectedMessage)));
    }
```


## A more complex scenario

The other sample scenario performs a POST query:

```gherkin


```

The _Given_ step uses ... the data in the Cucumber table with values defined in a properties 
file -to see how this works in detail, have a look at the `Class` class.

```java
todo
```

## Living documentation

You can generate full Serenity reports by running `mvn clean verify`. 
This includes both the living documentation from the feature files:

![](src/docs/rest-feature.png)

And also details of the REST requests and responses that were executed during the test:

![](src/docs/rest-report.png)


## Want to learn more?
For more information about Serenity BDD, you can read the [**Serenity BDD Book**](https://serenity-bdd.github.io/theserenitybook/latest/index.html), the official online Serenity documentation source. Other sources include:
