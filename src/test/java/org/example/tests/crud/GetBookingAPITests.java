package org.example.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.example.endpoints.APIConstants;
import static org.assertj.core.api.Assertions.assertThat;
import org.example.modules.FakerPaylaod;
import org.example.payloads.pojos.BookingResponse;
import org.example.payloads.pojos.base.BaseTest;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GetBookingAPITests extends BaseTest {

    @Owner("MARUF")
    @Test
    public void testGetBooking_ValidId_ShouldReturnBookingDetails() throws JsonProcessingException {
        // get an existing booking ID and verify all fields
        FakerPaylaod fakerPaylaod = new FakerPaylaod();
        requestSpecification .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(fakerPaylaod.fakerGson()).post();
        validatableResponse = response.then().log().all();
        jsonPath = jsonPath.from(response.asString());
        assertActions.verifyStatusCode(response, 200);
        // Direct Extraction from JSon Path
        bookingId = jsonPath.getString("bookingid");
        // Extraction using Booking Response class
        BookingResponse bookingResponse = payloadManager.JsonToObject(response.asString());
        bookingIdPojo = bookingResponse.getBookingid().toString();
        System.out.println("Booking Id " + jsonPath.getString("bookingid"));
        assertThat(bookingId).isNotBlank().isNotNull();

        response = RestAssured.given().spec(requestSpecification)
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId)
                .when().get();
        validatableResponse = response.then().log().all();

        // Verify key fields from the response
        String firstname =  response.jsonPath().getString("firstname");
        String lastname =  response.jsonPath().getString("lastname");
        int totalprice =  response.jsonPath().getInt("totalprice");
        boolean depositpaid = response.jsonPath().getBoolean("depositpaid");

        assertThat(firstname).isEqualTo(fakerPaylaod.getLastGeneratedFirstname());
        assertThat(lastname).isEqualTo(fakerPaylaod.getLastGeneratedLastname());
        assertThat(totalprice).isEqualTo(22);
        assertThat(depositpaid).isTrue();
    }

    @Owner("MARUF")
    @Test
    public void testGetBooking_InvalidId_ShouldReturnNotFound() {
        // use non-existing booking ID and expect 404
        String invalidBookingid = "9999999";
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + invalidBookingid);
        response = RestAssured.given().spec(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        assertActions.verifyStatusCodeIs4xx(response);
        String responseBody = response.asString();
        assertThat(responseBody).containsAnyOf("Not Found", "Booking not found", "Record not found");
    }

    @Owner("MARUF")
    @Test
    public void testGetBooking_NegativeId_ShouldReturnBadRequest() {
        // use -1 or negative number and expect 400
        String negativeBookingID = "-3383";
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + negativeBookingID);
        response = RestAssured.given().spec(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        assertActions.verifyStatusCodeIs4xx(response);
        String resBody = response.asString();
        assertThat(resBody).contains( "Bad Request");
    }

    @Owner("MARUF")
    @Test
    public void testGetBooking_NonNumericId_ShouldReturnBadRequest() {
        // use invalid ID like "abc" and expect 400 or error
            String nonNumericID = "abc";
            response = requestSpecification
                    .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + nonNumericID)
                    .when().get();
            validatableResponse = response.then().log().all();
            assertActions.verifyStatusCodeIs4xx(response);
            String resBody = response.asString();
            assertThat(resBody).contains("Bad Request");
    }

    @Owner("MARUF")
    @Test
    public void testGetBooking_MissingId_ShouldReturnMethodNotAllowed() {
        // call /booking without ID, expect 405 (method not allowed)
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL)
                .when().get();
        validatableResponse = response.then().log().all();
        assertActions.verifyStatusCodeIs4xx(response);
        String res = response.asString();
        assertThat(res).contains("Method not allowed");
    }

    @Owner("MARUF")
    @Test
    public void testGetBooking_ValidId_ResponseShouldContainRequiredFields() {
        // verify fields like firstname, lastname, totalprice, etc.
    }

    @Owner("MARUF")
    @Test
    public void testGetBooking_ValidId_CheckContentType_ShouldBeJson() throws JsonProcessingException {
        // verify response content type = application/json
        FakerPaylaod fakerPaylaod = new FakerPaylaod();
        requestSpecification .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(fakerPaylaod.fakerGson()).post();
        validatableResponse = response.then().log().all();
        jsonPath = jsonPath.from(response.asString());
        assertActions.verifyStatusCodeIs2xx(response);
        // Direct Extraction from JSon Path
        bookingId = jsonPath.getString("bookingid");
        // Extraction using Booking Response class
        BookingResponse bookingResponse = payloadManager.JsonToObject(response.asString());
        bookingIdPojo = bookingResponse.getBookingid().toString();
        System.out.println("Booking Id " + jsonPath.getString("bookingid"));
        assertThat(bookingId).isNotBlank().isNotNull();

        response = RestAssured.given().baseUri("https://restful-booker.herokuapp.com").pathParam("id", bookingId)
                .when().get("/booking/{id}");
        validatableResponse = response.then().log().all().statusCode(200).contentType("application/json");
    }

    @Owner("MARUF")
    @Test
    public void testGetBooking_ValidId_ResponseTime_ShouldBeUnder2Seconds() {
        // check response time assertion
    }





}
