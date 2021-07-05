package com.slack.requestpages;

import com.utilities.EndpointConstants;
import com.utilities.ReadPropertyFile;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ChatMe {

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

    @Step("User check chat me with invalid token")
    public void check_chat_me_with_invalid_token() {

        SerenityRest.given()
                .config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .contentType("application/x-www-form-urlencoded")
                .body("text=Welcome to the World&channel=C026XS7UZQR")
                .when()
                .post(ReadPropertyFile.form_endpoint_with_invalid_token(EndpointConstants.CHAT_ME_ENDPOINT));
    }
}
