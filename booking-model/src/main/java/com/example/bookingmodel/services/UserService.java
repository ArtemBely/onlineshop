package com.example.bookingmodel.services;


import com.example.bookingmodel.data.dto.CustomerDTO;
import com.example.bookingmodel.data.entity.CustomerEntity;
import com.example.bookingmodel.data.mapper.CustomerMapper;
import com.example.bookingmodel.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final DataSource dataSource;

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

    public Optional<CustomerDTO> updateUser(int id, CustomerDTO customerDTO) {
        log.info("Updating user with id: {}", id);
        return customerRepository.findById(id)
                .map(entity -> {
                    if (customerDTO.getName() != null) {
                        entity.setName(customerDTO.getName());
                    }
                    if (customerDTO.getSurname() != null) {
                        entity.setSurname(customerDTO.getSurname());
                    }
                    if (customerDTO.getPhone() != null) {
                        entity.setPhone(customerDTO.getPhone());
                    }
                    if (customerDTO.getDateofbirth() != null) {
                        entity.setDateOfBirth(customerDTO.getDateofbirth());
                    }
                    if (customerDTO.getEmail() != null) {
                        entity.setEmail(customerDTO.getEmail());
                    }
                    if (customerDTO.getPassword() != null) {
                        entity.setPassword(customerDTO.getPassword());
                    }

                    return customerMapper.mapToDto(customerRepository.save(entity));
                });
    }

    public void deleteUser(int id) {
        log.info("Deleting user with id: {}", id);
        customerRepository.deleteById(id);
    }

    @Transactional
    public void saveUserPhoto(int userId, byte[] photoBytes) {
        CustomerEntity user = customerRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPhoto(photoBytes);
        customerRepository.save(user);
    }
}