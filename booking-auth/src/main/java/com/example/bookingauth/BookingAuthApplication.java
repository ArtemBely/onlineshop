package com.example.bookingauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EntityScan({"com.example.*"})
public class BookingAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingAuthApplication.class, args);
    }

}
