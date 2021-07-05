# API Automation using Rest Assured Serenity Cucumber Library

This is repository of sample serenity cucumber based project automated using rest assured for Slack API's.

<img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white"/> <img alt="GitLab CI" src="https://img.shields.io/badge/GitLabCI-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=white"/> <img alt="Slack" src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white" /> 

## Tools/Framework/Libraries

- Maven - build tool
- Serenity rest assured library
- Cucumber - BDD/Gherkin style feature files
- GITLAB CI Pipeline
- Serenity HTML Report

## Project Structure
```
.
├── README.md
├── pom.xml                   Dependency for libraries
├── serenity.properties       Endpoints for API's
├── src
│   ├── main
│   └── test                  Test runners and supporting code 
└── target
    ├── classes
    ├── failsafe-reports
    ├── generated-test-sources
    ├── maven-archiver
    ├── maven-status
    ├── serenity-rest-cucumber-1.0.0-SNAPSHOT.jar
    ├── site                  Serenity HTML Report inside target/site/serenity/index.html
    └── test-classes

```

### How to run the test on local machine
- Prerequisites: maven3, java8 or greater
- Junit :
  - go to src/test/java/ and run class SlackAPITestRunner.java 
    (will run all test scenarios with **@regression** tag by default)
  - You can also modify the tags you want to execute from **@CucumberOptions** inside the class
- Maven :
  - run command from base project :
    
    ```bash
    $ mvn clean verify
    ```
    
    (will run all scenarios with @regression tag by default)
    
  - If you want to run different tags: 

    ```bash
    $ mvn clean verify -Dcucumber.filter.tags=@sanity
    ```
    
  - Serenity HTML report is generated when running the previous commands in target directory - open target/site/serenity/index.html after the execution.

## Below are instructions how to create new test in this project :-
BDD requires a feature file to invoke the step definitions:
Please find below step by step to understand how to implement :
- Create the scenarios in feature file as per the requirements,
  ```javascript
  Feature: Scenarios to test Slack chat messages on channel

  @sanity @regression
  Scenario: Verify a user able to share a me message into a slack channel
    When User share a chat me message to channel
    Then User should validate HTTP 200 response code
    And User validates the response of chat me message from slack channel
  ```  

- Once scenario added in feature file create corresponding steps in step definition file

  ```javascript
  @When("User share a chat me message to channel")
    public void share_a_chat_me_message() {
        chatMe.share_chat_me_to_channel();
    }
  
  @Then("User should validate HTTP 200 response code")
    public void http_200_response_code() {
          restAssuredThat(response -> response.statusCode(200));
    }
  
  @And("User validates the response of chat me message from slack channel")
    public void chat_me_response_validation(){
        restAssuredThat(response -> response.body(FilesResponseValidation.OK,
                equalTo(true)));
        restAssuredThat(response -> response.body(FilesResponseValidation.CHANNEL,
                equalTo(ReadPropertyFile.readConfigurationFile(EndpointConstants.CHANNEL_ID))));

    }
  ```
  
  so each step in feature file has to match a step definition in class file;
- Keep endpoints in serenity.properties file which are needed in test and add those variable constants in EndpointConstants.java file 
  
  ```
  Chat_Me_EndPoint=/chat.meMessage?token=
  
  Here initial domain is passed directly from pom.xml so no need of passing again in property file
  **<domain>https://slack.com/api</domain>**
  ```
- After that use serenity rest assured library to create background code for api's and keep that code inside the request pages

  ```javascript
  @Step("User share a chat me to channel")
    public void share_chat_me_to_channel() {
        SerenityRest.given()
                .config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .contentType("application/x-www-form-urlencoded")
                .body("text=Welcome to the World&channel=C026XS7UZQR")
                .when()
                .post(ReadPropertyFile.form_endpoint(EndpointConstants.CHAT_ME_ENDPOINT));
    }
  ```
- To Validate response code and body from api add variable inside validation folder

  ```javascript
   public static final String OK = "'ok'"; 
   public static final String AUTH_TEST_URL = "'url'";
  
  And call that in response verification like below
  
   @And("User validates the response of chat me message from slack channel")
    public void chat_me_response_validation(){
        restAssuredThat(response -> response.body(FilesResponseValidation.OK,
                equalTo(true)));
        restAssuredThat(response -> response.body(FilesResponseValidation.CHANNEL,
                equalTo(ReadPropertyFile.readConfigurationFile(EndpointConstants.CHANNEL_ID))));

    }
  ```