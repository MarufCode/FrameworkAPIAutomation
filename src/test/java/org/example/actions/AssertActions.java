package org.example.actions;

import io.restassured.response.Response;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class AssertActions {


        // Verify exact status code
        public void verifyStatusCode(Response response, int expectedStatusCode) {
            Assert.assertNotNull(response, "Response is null");
            int actual = response.getStatusCode();
            Assert.assertEquals(actual, expectedStatusCode,
                    "Expected status code " + expectedStatusCode + " but was " + actual);
        }

        // Verify status code is in 2xx family
        public void verifyStatusCodeIs2xx(Response response) {
            Assert.assertNotNull(response, "Response is null");
            int actual = response.getStatusCode();
            Assert.assertTrue(actual >= 200 && actual < 300,
                    "Expected 2xx status code but was " + actual);
        }

        // Verify status code is in 4xx family
        public void verifyStatusCodeIs4xx(Response response) {
            Assert.assertNotNull(response, "Response is null");
            int actual = response.getStatusCode();
            Assert.assertTrue(actual >= 400 && actual < 500,
                    "Expected 4xx status code but was " + actual);
        }

        // Verify status code is in 5xx family
        public void verifyStatusCodeIs5xx(Response response) {
            Assert.assertNotNull(response, "Response is null");
            int actual = response.getStatusCode();
            Assert.assertTrue(actual >= 500 && actual < 600,
                    "Expected 5xx status code but was " + actual);
        }

        // General response body equality for String
        public void verifyResponseBody(String actual, String expected, String description) {
            Assert.assertEquals(actual, expected, description);
        }

        // Numeric overloads
        public void verifyResponseBody(float actual, float expected, String description) {
            Assert.assertEquals(actual, expected, description);
        }

        public void verifyResponseBody(int actual, int expected, String description) {
            Assert.assertEquals(actual, expected, description);
        }

        public void verifyResponseBody(double actual, double expected, String description) {
            Assert.assertEquals(actual, expected, description);
        }

        public void verifyResponseBody(boolean actual, boolean expected, String description) {
            Assert.assertEquals(actual, expected, description);
        }
    }


//
//
//    public void verifyStatusCode(Response response) {
//        assertEquals(String.valueOf(response.getStatusCode()).startsWith("20"), true,
//                "value of status code is" + response.getStatusCode());
//    }
//
//    public void verifyStatusCodeInvalidReq(Response response) {
//        assertEquals(String.valueOf(response.getStatusCode()).startsWith("50"), true,
//                "value of status code is" + response.getStatusCode());
//    }
//
//    public void verifyResponseBody(String actual, String expected, String description) {
//        assertEquals(actual, expected, description);
//
//    }
//
//    public void verifyResponseBody(float actual, float expected, String description) {
//        assertEquals(actual, expected, description);
//
//    }
//
//    public void verifyResponseBody(int actual, int expected, String description) {
//        assertEquals(actual, expected, description);
//
//    }
//
//    public void verifyResponseBody(double actual, double expected, String description) {
//        assertEquals(actual, expected, description);
//
//    }
//
//    public void verifyResponseBody(boolean actual, boolean expected, String description) {
//        assertEquals(actual, expected, description);
//
//    }
//}
