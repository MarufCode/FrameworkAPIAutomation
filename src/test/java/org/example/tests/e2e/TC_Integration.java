package org.example.tests.e2e;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.endpoints.APIConstants;
import org.example.payloads.pojos.Booking;
import org.example.payloads.pojos.BookingResponse;
import org.example.payloads.pojos.base.BaseTest;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class TC_Integration extends BaseTest {


    String token;

    String bookingId;

    String bookingIdPojo;

    // Get Token
    // Create Booking
    // Update the booking with booking ID and Token
    // 1. Authentication
    // Delete the booking





    // Create Booking
    @Test(groups = "P0")
    public void testCreateBooking() throws JsonProcessingException {
        token = getToken();
        assertThat(token).isNotNull().isNotEmpty().isNotBlank();

        requestSpecification .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();
        validatableResponse = response.then().log().all();
        jsonPath = jsonPath.from(response.asString());
        validatableResponse.statusCode(200);

        // Direct Extraction from JSon Path
        bookingId = jsonPath.getString("bookingid");

        // Extraction using Booking Response class
        BookingResponse bookingResponse = payloadManager.JsonToObject(response.asString());
        bookingIdPojo = bookingResponse.getBookingid().toString();


        System.out.println("Booking Id" + jsonPath.getString("bookingid"));

        assertThat(bookingId).isNotBlank().isNotNull();
    }




    // Update the booking with booking ID and Token
    @Test(groups = "P0", dependsOnMethods = {"testCreateBooking"})
    public void testUpdateBookingPut() throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token", token)
                .when().body(payloadManager.updatePayload()).put();
        validatableResponse = response.then().log().all();
//        validatableResponse.body("firstname", Matchers.is("Maruf"));

        Booking bookingResponse = payloadManager.JsonToObjectPUT(response.asString());
        assertThat(bookingResponse.getFirstname().toString()).isEqualTo("Maruf").isNotNull();
        assertThat(bookingResponse.getLastname()).isNotNull();
        assertThat(bookingResponse.getDepositpaid()).isNotNull();
        assertThat(bookingResponse.getBookingdates().getCheckin()).isNotNull();
        assertThat(bookingResponse.getBookingdates().getCheckout()).isNotNull().isNotEmpty();
    }

    @Test(groups = "P0", dependsOnMethods = {"testCreateBooking", "testUpdateBookingPut"})
    public void testUpdateBookingPatch() throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token", token)
                .when().body(payloadManager.updatePayloadPatch()).patch();
        validatableResponse = response.then().log().all();
//        validatableResponse.body("firstname", Matchers.is("Maruf"));

        Booking bookingResponse = payloadManager.JsonToObjectPUT(response.asString());
        assertThat(bookingResponse.getFirstname().toString()).isEqualTo("MahiBhai").isNotNull();
        assertThat(bookingResponse.getLastname()).isNotNull();
        assertThat(bookingResponse.getDepositpaid()).isNotNull();
        assertThat(bookingResponse.getBookingdates().getCheckin()).isNotNull();
        assertThat(bookingResponse.getBookingdates().getCheckout()).isNotNull().isNotEmpty();
    }




    // Delete the booking
    @Test(groups = "P0", dependsOnMethods = {"testUpdateBookingPut"})
    public void testDeleteBooking(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin", "password123")
                        .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }

    @Test(groups = "P0", dependsOnMethods = {"testDeleteBooking"})
    public void testDeleteBookingByGet(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().get().then().log().all();
        validatableResponse.statusCode(404);
//        assertThat(validatableResponse.statusCode(404)).isNotNull();
    }




}
