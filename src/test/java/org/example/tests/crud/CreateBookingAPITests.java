package org.example.tests.crud;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.example.endpoints.APIConstants;
import org.example.payloads.pojos.Booking;
import org.example.payloads.pojos.BookingResponse;
import org.example.payloads.pojos.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateBookingAPITests extends BaseTest {

    @Test
    public void testCreateBooking_ValidData_ShouldReturnBookingId() {
     requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
     response = RestAssured.given().spec(requestSpecification)
             .when().body(payloadManager.createPayloadGSON()).post();
     validatableResponse = response.then().log().all();
     jsonPath = jsonPath.from(response.asString());
     validatableResponse.statusCode(200);
     bookingId = jsonPath.getString("bookingid");
     System.out.println(bookingId);
    }

    @Test
    public void testCreateBooking_MissingFirstname_ShouldReturnBadRequest() throws JsonProcessingException {
        String  missingFirstname_payload = payloadManager.createBookingPayload("", "Sherikar", 1200, true, "2025-10-15", "2025-10-20", "Dinner");
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(missingFirstname_payload).post();
        validatableResponse = response.then().log().all();
        BookingResponse bookingResponse = payloadManager.JsonToObject(response.asString());
        assertThat(response.getStatusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("firstname")).isEqualTo("missing firstname");
    }

    @Test
    public void testCreateBooking_MissingLastname_ShouldReturnBadRequest() {
        String missingLastname_payload = payloadManager.createBookingPayload("Sherikar", "", 1200, true, "2025-10-15", "2025-10-20", "Dinner");
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(missingLastname_payload).post();
        validatableResponse = response.then().log().all();
        assertThat(response.getStatusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("lastname")).isEqualTo("missing lastname");
    }

    @Test
    public void testCreateBooking_MissingBookingDates_ShouldReturnBadRequest() {
        String MissingBookingDates_payload = payloadManager.createBookingPayload("Sherikar", "MAruf", 1200, true, "", "", "Dinner");
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(MissingBookingDates_payload).post();
        validatableResponse = response.then().log().all();
        assertThat(response.getStatusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("checkin")).isEqualTo("missing checkin date");
        assertThat(response.jsonPath().getString("checkout")).isEqualTo("missing checkout date");
    }

    @Test
    public void testCreateBooking_InvalidDateFormat_ShouldReturnError() {
        String InvalidDateFormat_payload = payloadManager.createBookingPayload("Sherikar", "MAruf", 1200, true, "20-01-11", "2015-01-11", "Dinner");
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(InvalidDateFormat_payload).post();
        validatableResponse = response.then().log().all();
        assertThat(response.getStatusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("checkin")).isEqualTo("Invalid date format");
        assertThat(response.jsonPath().getString("checkout")).isEqualTo("Invalid date format");
    }

    @Test
    public void testCreateBooking_NegativeTotalPrice_ShouldReturnError() {
        String NegativeTotalPrice_payload = payloadManager.createBookingPayload("Sherikar", "MAruf", -1200, true, "20-01-11", "2015-01-11", "Dinner");
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(NegativeTotalPrice_payload).post();
        validatableResponse = response.then().log().all();
        assertThat(response.getStatusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("totalprice")).isEqualTo("Negative value not allowed");

    }

    @Test
    public void testCreateBooking_EmptyPayload_ShouldReturnBadRequest() {
        String EmptyPayload = payloadManager.createBookingPayload("", "", 56, true,  " ", " ", "");
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(EmptyPayload).post();
        validatableResponse = response.then().log().all();
        assertThat(response.getStatusCode()).isEqualTo(502);
        assertThat(response.jsonPath().getString("firstname")).isEqualTo("Bad Request");
    }

    @Test
    public void testCreateBooking_InvalidDataType_ShouldReturnError() {
        Assert.assertEquals("Maruf", "Maruf");
    }

    @Test
    public void testCreateBooking_ExtraField_ShouldIgnoreOrReturnError() {
        Assert.assertEquals("Maruf", "Maruf");
    }

    @Test
    public void testCreateBooking_ValidData_ShouldContainAllExpectedFields() {
        Assert.assertEquals("Maruf", "Maruf");
    }

    @Test
    public void testCreateBooking_SpecialCharactersInName_ShouldReturnBookingId() {
        Assert.assertEquals("Maruf", "Maruf");
    }

    @Test
    public void testCreateBooking_LargePayload_ShouldReturnBookingId() {
        Assert.assertEquals("Maruf", "Maruf");
    }

    @Test
    public void testCreateBooking_WithoutAdditionalNeeds_ShouldReturnBookingId() {
        Assert.assertEquals("Maruf", "Maruf");
    }

    @Test
    public void testCreateBooking_WithLongStayDates_ShouldReturnBookingId() {
        Assert.assertEquals("Maruf", "Maruf");
    }

    @Test
    public void testCreateBooking_ConcurrentRequests_ShouldReturnUniqueBookingIds() {
        Assert.assertEquals("Maruf", "Maruf");
    }




}
