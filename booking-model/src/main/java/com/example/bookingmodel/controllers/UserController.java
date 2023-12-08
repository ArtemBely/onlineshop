package com.example.bookingmodel.controllers;

import com.example.bookingmodel.data.dto.CustomerDTO;
import com.example.bookingmodel.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



//    @PostMapping("/user/{userId}/photo")
//    public ResponseEntity<Void> uploadUserPhoto(@PathVariable Integer userId, @RequestParam("photo") MultipartFile photo) throws IOException {
//        byte[] photoBytes = photo.getBytes();
//        userPhotoService.saveUserPhoto(userId, photoBytes);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/user/{userId}/photo")
//    public ResponseEntity<Void> updateUserPhoto(@PathVariable Integer userId, @RequestParam("photo") MultipartFile photo) throws IOException {
//        userPhotoService.updateUserPhoto(userId, photo.getBytes());
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/user/{userId}/photo")
//    public ResponseEntity<Void> deleteUserPhoto(@PathVariable Integer userId) {
//        userPhotoService.deleteUserPhoto(userId);
//        return ResponseEntity.ok().build();
//    }

}
