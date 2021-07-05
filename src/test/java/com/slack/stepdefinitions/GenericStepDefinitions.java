package com.slack.stepdefinitions;

import com.slack.requestpages.ChatMe;
import com.slack.requestpages.ChatsPostEphemeralAPI;
import com.slack.requestpages.FilesAPI;
import com.slack.validation.AuthTestResponseValidation;
import com.slack.validation.FilesResponseValidation;
import com.utilities.DataStore;
import com.utilities.EndpointConstants;
import com.utilities.JsonFileReader;
import com.utilities.ReadPropertyFile;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import static org.hamcrest.Matchers.equalTo;


import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class GenericStepDefinitions {

    @Steps
    ChatMe chatMe;

    @Steps
    ChatsPostEphemeralAPI chatsPostEphemeralAPI;

    @Steps
    FilesAPI filesAPI;

    @Given("User sends post request to auth test to get user id")
    public void getAuthTest() {
        chatsPostEphemeralAPI.get_user_id_from_auth_test();
    }

    @When("User share a chat me message to channel")
    public void share_a_chat_me_message() {
        chatMe.share_chat_me_to_channel();
    }

    @When("User sends a get request to fetch Info of file {} from a channel")
    public void file_info_from_channel(String fileID) {
        filesAPI.get_file_info_from_channel(fileID);
    }

    @When("User sends a get request to fetch Info of file from a channel with invalid auth")
    public void invalid_auth_file_info() {
        filesAPI.get_file_info_from_invalid_auth();
    }

    @When("User check chat me with invalid token")
    public void chat_me_invalid_token() {
        chatMe.check_chat_me_with_invalid_token();
    }

    @When("User sends an ephemeral message to a user in a channel")
    public void sends_ephemeral_message() {
        chatsPostEphemeralAPI.sends_an_ephemeral_message_to_channel();
    }

    @When("User sends an ephemeral message to a user not present in a channel")
    public void sends_ephemeral_message_user_not_present() {
        chatsPostEphemeralAPI.sends_an_ephemeral_message_to_channel_user_not_present();
    }

    @Then("User should validate HTTP 200 response code")
    public void http_200_response_code() {
          restAssuredThat(response -> response.statusCode(200));
    }

    @Then("User validates response of file {} info from slack")
    public void file_info_valid_response(String fileID) {
        JsonFileReader.getJSONConfigData();
        restAssuredThat(response -> response.body(FilesResponseValidation.OK,
                equalTo(true)));
        restAssuredThat(response -> response.body(FilesResponseValidation.FILE_ID,
                equalTo(fileID)));
        restAssuredThat(response -> response.body(FilesResponseValidation.FILE_MIME_TYPE,
                equalTo(DataStore.getAuthInfo("File_API").getString("Mimetype"))));
        restAssuredThat(response -> response.body(FilesResponseValidation.FILE_USER_ID,
                equalTo(DataStore.getAuthInfo("File_API").getString("User_id"))));

    }

    @Then("User validates error message {} from response")
    public void error_msg_response(String error_message) {
        restAssuredThat(response -> response.body(FilesResponseValidation.OK,
                equalTo(false)));
        restAssuredThat(response -> response.body(FilesResponseValidation.FILE_ERROR,
                equalTo(error_message)));
    }

    @And("User validates the response of chat me message from slack channel")
    public void chat_me_response_validation(){
        restAssuredThat(response -> response.body(FilesResponseValidation.OK,
                equalTo(true)));
        restAssuredThat(response -> response.body(FilesResponseValidation.CHANNEL,
                equalTo(ReadPropertyFile.readConfigurationFile(EndpointConstants.CHANNEL_ID))));

    }

    @And("User validates the response of ephemeral message from slack channel")
    public void ephemeral_response_validation(){
        restAssuredThat(response -> response.body(FilesResponseValidation.OK,
                equalTo(true)));
    }

    @And("User validates the response of auth test from slack channel")
    public void response_auth_test_validation(){
        JsonFileReader.getJSONConfigData();
        restAssuredThat(response -> response.body(AuthTestResponseValidation.OK,
                equalTo(true)));
        restAssuredThat(response -> response.body(AuthTestResponseValidation.AUTH_TEST_URL,
                equalTo(DataStore.getAuthInfo("Auth_API").getString("Auth_test_url"))));
        restAssuredThat(response -> response.body(AuthTestResponseValidation.TEAM,
                equalTo(DataStore.getAuthInfo("Auth_API").getString("Team"))));
        restAssuredThat(response -> response.body(AuthTestResponseValidation.TEAM_ID,
                equalTo(DataStore.getAuthInfo("Auth_API").getString("Team_id"))));
        restAssuredThat(response -> response.body(AuthTestResponseValidation.USER_ID,
                equalTo(DataStore.getAuthInfo("Auth_API").getString("User_id"))));
    }
}