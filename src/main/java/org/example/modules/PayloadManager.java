package org.example.modules;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.example.payloads.pojos.Auth;
import org.example.payloads.pojos.Booking;
import org.example.payloads.pojos.BookingResponse;
import org.example.payloads.pojos.Bookingdates;

import java.util.Arrays;

public class PayloadManager{


    public String createPayloadGSON(){
        Faker faker = new Faker();
        Booking booking = new Booking();
        String expectedFirstName = faker.name().firstName();
        booking.setFirstname(expectedFirstName);
        String expectedLastName = faker.name().lastName();
        booking.setLastname(expectedLastName);
        booking.setTotalprice(22);
        booking.setDepositpaid(true);


        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2025-10-01");
        bookingdates.setCheckout("2025-10-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds(Arrays.asList("Breakfast", "Lunch", "Dinner"));

        System.out.println(booking);

        // Object -> JSON String by using GSON

        Gson gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        System.out.println(jsonStringBooking);

        return jsonStringBooking;
    }


    public String updatePayload() throws JsonProcessingException {
        Faker faker = new Faker();
        ObjectMapper objectMapper1 = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Maruf");
        String expectedLastName = faker.name().lastName();
        booking.setLastname(expectedLastName);
        booking.setTotalprice(22);
        booking.setDepositpaid(true);


        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2025-10-01");
        bookingdates.setCheckout("2025-10-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds(Arrays.asList("Breakfast", "Lunch", "Dinner"));
        String payload = objectMapper1.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;

    }

    public String updatePayloadPatch() throws JsonProcessingException {
        Faker faker = new Faker();
        ObjectMapper objectMapper1 = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("MahiBhai");
        String expectedLastName = faker.name().lastName();
        booking.setLastname(expectedLastName);
        booking.setTotalprice(22);
        booking.setDepositpaid(true);


        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2025-10-01");
        bookingdates.setCheckout("2025-10-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds(Arrays.asList("Breakfast", "Lunch", "Dinner"));
        String payload = objectMapper1.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;

    }


    private  ObjectMapper objectMapper = new ObjectMapper();
    public String setToken() throws JsonProcessingException {
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        return objectMapper.writeValueAsString(auth);
    }

    public String invalidUsernameTokenCred() throws JsonProcessingException {
        Auth auth = new Auth();
        auth.setUsername("ADMIN");
        auth.setPassword("password123");
        return objectMapper.writeValueAsString(auth);
    }

    public String invalidPasswordTokenCred() throws JsonProcessingException {
        Auth auth = new Auth();
        auth.setUsername("ADMIN");
        auth.setPassword("password12");
        return objectMapper.writeValueAsString(auth);
    }

    public String MissingCredentials() throws JsonProcessingException {
        Auth auth = new Auth();
        return objectMapper.writeValueAsString(auth);
    }



    public BookingResponse JsonToObject(String jsonString) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        BookingResponse bookingResponse = objectMapper.readValue(jsonString, BookingResponse.class);
        return bookingResponse;
    }


    public Booking JsonToObjectPUT(String jsonString) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking bookingResponse = objectMapper.readValue(jsonString, Booking.class);
        return bookingResponse;
    }


    // Default payload (for most tests)
    public String createBookingPayload(String firstName, String lastName, int totalPrice,
                                       boolean depositPaid, String checkin, String checkout, String needs) {
        return "{\n" +
                "  \"firstname\": \"" + firstName + "\",\n" +
                "  \"lastname\": \"" + lastName + "\",\n" +
                "  \"totalprice\": " + totalPrice + ",\n" +
                "  \"depositpaid\": " + depositPaid + ",\n" +
                "  \"bookingdates\": {\n" +
                "    \"checkin\": \"" + checkin + "\",\n" +
                "    \"checkout\": \"" + checkout + "\"\n" +
                "  },\n" +
                "  \"additionalneeds\": \"" + needs + "\"\n" +
                "}";
    }

}
