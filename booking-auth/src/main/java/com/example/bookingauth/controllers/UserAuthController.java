package com.example.bookingauth.controllers;

import com.example.bookingauth.services.AuthenticationRequest;
import com.example.bookingauth.services.AuthenticationResponse;
import com.example.bookingauth.services.UserAuthService;
import com.example.bookingmodel.data.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController("userAuthController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/create")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody CustomerDTO request) {
        log.info("start of registration process");
        return ResponseEntity.ok(userAuthService.register(request));
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        log.info("start to insert user");
        return ResponseEntity.ok(userAuthService.authenticate(request));
    }

    @PostMapping("/match")
    public boolean matchPass(@Valid @RequestBody CustomerDTO customerDTO) {
        log.info("start to insert user");
        return userAuthService.matchPassword(customerDTO);
    }
}
