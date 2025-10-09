package org.example.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.example.payloads.pojos.Booking;
import org.example.payloads.pojos.Bookingdates;

import java.util.Arrays;

public class PayloadManager {

    //JAVA -> GSON



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





    public void createPayloadJackSon(){

    }

    public void updatePayload(){

    }



}
