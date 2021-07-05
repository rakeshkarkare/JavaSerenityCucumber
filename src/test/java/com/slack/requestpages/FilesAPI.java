package com.slack.requestpages;

import com.utilities.EndpointConstants;
import com.utilities.ReadPropertyFile;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class FilesAPI {

    @Step("User sends get request for file {0} Info")
    public void get_file_info_from_channel(String FileID) {
        SerenityRest.given()
                .pathParam("file", FileID)
                .get(ReadPropertyFile.form_endpoint(EndpointConstants.FILE_INFO_API_ENDPOINT));
    }

    @Step("User sends get request for file with invalid auth")
    public void get_file_info_from_invalid_auth() {
        SerenityRest.given()
                .pathParam("file", "F026UC1J7GV")
                .get(ReadPropertyFile.form_endpoint_with_invalid_token(EndpointConstants.FILE_INFO_API_ENDPOINT));
    }

}
