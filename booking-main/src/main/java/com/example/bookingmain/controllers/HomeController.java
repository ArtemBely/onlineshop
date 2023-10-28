package com.example.bookingmain.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@RestController("homeMainControllerImplementation")
@RequiredArgsConstructor
@RequestMapping("/test")
public class HomeController {

    @GetMapping("/hello")
    public String getHello() {
        System.out.println("HELLO");
        return "Hello there";
    }
}
