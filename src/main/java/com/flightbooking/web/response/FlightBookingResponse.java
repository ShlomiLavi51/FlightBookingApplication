package com.flightbooking.web.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightBookingResponse {
    private final long timestamp;
    private final String message;

    public static FlightBookingResponse ofNow(String message) {
        return new FlightBookingResponse(System.currentTimeMillis(), message);
    }
}
