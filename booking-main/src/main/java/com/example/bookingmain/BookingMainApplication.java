package com.example.bookingmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.bookingmain", "com.example.bookingauth", "com.example.bookingmodel"})
public class BookingMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingMainApplication.class, args);
    }

}
