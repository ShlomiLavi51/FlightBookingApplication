package com.flightbooking.web.controller.login;

import com.flightbooking.entity.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserCredentials {
    private String email;
    private String password;
    private UserType clientType;
    private final String token;

}
