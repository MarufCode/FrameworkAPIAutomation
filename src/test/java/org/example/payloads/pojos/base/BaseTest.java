package org.example.payloads.pojos.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.actions.AssertActions;
import org.example.endpoints.APIConstants;
import org.example.modules.PayloadManager;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public RequestSpecification requestSpecification;

    public Response response;

    public AssertActions assertActions;

    public PayloadManager payloadManager;

    public JsonPath jsonPath;

    public ValidatableResponse validatableResponse;


    @BeforeMethod
    public void setConfig() {
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();



    }
}