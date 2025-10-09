package org.example.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.payloads.pojos.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.testng.annotations.Test;

public class TCCreateBooking extends BaseTest {



    @Owner("MARUF")
    @Description("Verify that the Create Booking with the valid Payload, Status Code 200")
    @Test
    public void testPositivePostReq(){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        System.out.println();





    }
}
