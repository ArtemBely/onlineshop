package com.example.bookingmodel.services;


import com.example.bookingmodel.data.dto.CustomerDTO;
import com.example.bookingmodel.data.entity.CustomerEntity;
import com.example.bookingmodel.data.mapper.CustomerMapper;
import com.example.bookingmodel.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public List<CustomerDTO> findAllUsers() {
        log.info("Users finding...");
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<CustomerDTO> findUserByEmail(String email) {
        log.info("Searching for user with email: {}", email);
        return customerRepository.findCustomerEntitiesByEmail(email)
                .map(customerMapper::mapToDto);
    }
}