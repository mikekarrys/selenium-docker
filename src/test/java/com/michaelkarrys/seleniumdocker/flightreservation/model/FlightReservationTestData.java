package com.michaelkarrys.seleniumdocker.flightreservation.model;

public record FlightReservationTestData(String firstname,
                                        String lastname,
                                        String email,
                                        String password,
                                        String street,
                                        String city,
                                        String zip,
                                        String passengersCount,
                                        String expectedPrice ) {
}
