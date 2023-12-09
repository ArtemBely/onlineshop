package com.example.bookingmodel.controllers;

import com.example.bookingmodel.data.dto.CustomerDTO;
import com.example.bookingmodel.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PutMapping("/user/{id}")
    public ResponseEntity<CustomerDTO> updateUser(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        log.info("Updating user with id: {}", id);
        return userService.updateUser(id, customerDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        log.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/user/{id}/photo")
    public ResponseEntity<Void> uploadUserPhoto(@PathVariable("id") int userId,
                                                @RequestParam("photo") MultipartFile photo) throws IOException {
        byte[] photoBytes = photo.getBytes();
        userService.saveUserPhoto(userId, photoBytes);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{id}/photo")
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable int id) {
        byte[] photoBytes = userService.getUserPhoto(id);
        if (photoBytes == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // or the appropriate content type

        return new ResponseEntity<>(photoBytes, headers, HttpStatus.OK);
    }


}
