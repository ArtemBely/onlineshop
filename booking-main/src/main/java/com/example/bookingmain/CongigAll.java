package com.example.bookingmain;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.jackson.JacksonEncoder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.example.bookingmain")
//@EnableJpaRepositories("com.example.bookingmain.repositories")
@Configuration
public class CongigAll {

    @Bean
    public JacksonEncoder feignEncoder() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new JacksonEncoder(objectMapper);
    }

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }

}
