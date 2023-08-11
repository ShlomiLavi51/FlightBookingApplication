package com.flightbooking;


import lombok.Getter;


@Getter
public record LoginResponse(boolean success, String message, long timestamp, String token) {


    public static LoginResponse from(Throwable ex) {
        return new LoginResponse(false, ex.getMessage(), System.currentTimeMillis(), "");
    }


    public static LoginResponse ofSuccess(String token) {
        return new LoginResponse(true, "", System.currentTimeMillis(), token);
    }

}
