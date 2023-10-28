package com.example.bookingauth.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("encryptionService")
@AllArgsConstructor
public class EncryptionService {

    private final PasswordEncoder passwordEncoder;

    public String getEncodePass(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }

}
