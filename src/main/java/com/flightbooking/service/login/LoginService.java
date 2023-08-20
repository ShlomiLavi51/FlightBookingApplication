package com.flightbooking.service.login;

import com.flightbooking.web.ClientSession;
import com.flightbooking.web.controller.login.UserCredentials;

public interface LoginService {
    ClientSession createSession(UserCredentials userCredentials);

    String generateToken();
}
