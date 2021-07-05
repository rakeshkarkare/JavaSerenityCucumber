package com.slack.requestpages;

import com.utilities.EndpointConstants;
import io.restassured.config.EncoderConfig;
import com.utilities.ReadPropertyFile;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ChatsPostEphemeralAPI {

    @Step("User sends auth test to get token")
    public void get_user_id_from_auth_test() {
        SerenityRest.given()
                .config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post(ReadPropertyFile.form_endpoint(EndpointConstants.AUTH_TEST_API_ENDPOINT));
    }

    @Step("User sends an ephemeral message to a user in a channel")
    public void sends_an_ephemeral_message_to_channel() {
        SerenityRest.given()
                .config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .contentType("application/x-www-form-urlencoded")
                .body("attachments=[{text=Hello+Machine}]&text=Hello+Machine&user=U026G3W5Q13&channel=C026XS7UZQR")
                .when()
                .post(ReadPropertyFile.form_endpoint(EndpointConstants.EPHEMERAL_API_ENDPOINT));
    }

    @Step("User sends an ephemeral message to a user not present in a channel")
    public void sends_an_ephemeral_message_to_channel_user_not_present() {
        SerenityRest.given()
                .config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .contentType("application/x-www-form-urlencoded")
                .body("attachments=[{text=Hello+Machine}]&text=Hello+Machine&user=RKTEST&channel=C026XS7UZQR")
                .when()
                .post(ReadPropertyFile.form_endpoint(EndpointConstants.EPHEMERAL_API_ENDPOINT));
    }

}
