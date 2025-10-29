package org.example.modules;


import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.example.payloads.pojos.Auth;
import org.example.payloads.pojos.Booking;
import org.example.payloads.pojos.BookingResponse;
import org.example.payloads.pojos.Bookingdates;

import java.util.Arrays;

public class FakerPaylaod {


        private String lastGeneratedFirstname;
        private String lastGeneratedLastname;

        public String fakerGson() {
            Faker faker = new Faker();
            Booking booking = new Booking();

            lastGeneratedFirstname = faker.name().firstName();
            lastGeneratedLastname = faker.name().lastName();

            booking.setFirstname(lastGeneratedFirstname);
            booking.setLastname(lastGeneratedLastname);
            booking.setTotalprice(22);
            booking.setDepositpaid(true);

            Bookingdates bookingdates = new Bookingdates();
            bookingdates.setCheckin("2025-10-01");
            bookingdates.setCheckout("2025-10-01");
            booking.setBookingdates(bookingdates);
            booking.setAdditionalneeds(Arrays.asList("Breakfast", "Lunch", "Dinner"));

            return new Gson().toJson(booking);
        }

        public String getLastGeneratedFirstname() { return lastGeneratedFirstname; }
        public String getLastGeneratedLastname() { return lastGeneratedLastname; }


}
