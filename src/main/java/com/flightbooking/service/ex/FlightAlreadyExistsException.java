package com.flightbooking.service.ex;

public class FlightAlreadyExistsException extends RuntimeException {
    public FlightAlreadyExistsException(String msg) {
        super(msg);
    }
}
