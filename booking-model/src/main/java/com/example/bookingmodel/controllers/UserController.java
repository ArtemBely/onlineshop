package com.example.bookingmodel.controllers;

import com.example.bookingmodel.data.dto.CustomerDTO;
import com.example.bookingmodel.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController("userController")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<CustomerDTO> getUsres() {
        log.info("Start to retrieve all users...");
        return userService.findAllUsers();
    }

    @GetMapping("/user")
    public ResponseEntity<CustomerDTO> getUserByEmail(@RequestParam String email) {
        log.info("Fetching user with email: {}", email);
        return userService.findUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
