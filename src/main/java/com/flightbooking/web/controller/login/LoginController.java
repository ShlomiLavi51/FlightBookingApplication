package com.flightbooking.web.controller.login;

import com.couponsystem.LoginResponse;
import com.couponsystem.service.ex.InvalidLoginException;
import com.couponsystem.service.login.LoginService;
import com.couponsystem.web.ClientSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LoginController {
    private final LoginService loginService;
    private final Map<String, ClientSession> tokens;

    @Autowired
    public LoginController(LoginService loginService,
                           @Qualifier("tokens") Map<String, ClientSession> tokens) {
        this.loginService = loginService;
        this.tokens = tokens;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserCredentials credentials) {
        try {
            String token = loginService.generateToken();
            ClientSession session = loginService.createSession(credentials);
            tokens.put(token, session);

            return ResponseEntity.ok(LoginResponse.ofSuccess(token));
        } catch (InvalidLoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
