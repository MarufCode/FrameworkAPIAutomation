package org.example.tests.auth_Token;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.example.endpoints.APIConstants;
import org.example.payloads.pojos.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class AuthAPITests extends BaseTest {

        @Owner("Maruf")
        @Description("Verify that test should return Token")
        @Test
        public void testValidCredentials_ShouldReturnToken() throws JsonProcessingException {
            requestSpecification.basePath(APIConstants.GET_TOKEN_URL);
            response = RestAssured.given().spec(requestSpecification)
                    .when().body(payloadManager.setToken()).post();

            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);
        }

        @Owner("Maruf")
        @Description("Verify that test should return Empty data for Invalid Username")
        @Test
        public void testInvalidUsername_ShouldReturnEmptyToken() throws JsonProcessingException {
           requestSpecification.basePath(APIConstants.GET_TOKEN_URL);
           response = RestAssured.given().spec(requestSpecification)
                   .when().body(payloadManager.invalidUsernameTokenCred()).post();
           validatableResponse = response.then().log().all();
           validatableResponse.statusCode(200);
           assertThat(response.jsonPath().getString("token")).isNull();
           assertThat(response.jsonPath().getString("reason"));
        }

        @Owner("Maruf")
        @Description("Verify that test should return Empty data for Invalid Password")
        @Test
        public void testInvalidPassword_ShouldReturnEmptyToken() throws JsonProcessingException {
            requestSpecification.basePath(APIConstants.GET_TOKEN_URL);
            response = RestAssured.given().spec(requestSpecification)
                    .when().body(payloadManager.invalidPasswordTokenCred()).post();
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);
            assertThat(response.jsonPath().getString("token")).isNull();
            assertThat(response.jsonPath().getString("reason"));
        }

        @Owner("Maruf")
        @Description("Verify that test should return Bad Request for missing Credentials")
        @Test
        public void testMissingCredentials_ShouldReturnBadRequest() throws JsonProcessingException {
            requestSpecification.basePath(APIConstants.GET_TOKEN_URL);
            response = RestAssured.given().spec(requestSpecification)
                    .when().body(payloadManager.MissingCredentials()).post();
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);
            assertThat(response.jsonPath().getString("token")).isNull();
            assertThat(response.jsonPath().getString("reason"));
        }

}
