package com.maneira.newsletter.resources;

import com.maneira.newsletter.entities.*;
import com.maneira.newsletter.dto.UserDTO;
import com.maneira.newsletter.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InterestRepository interestRepo;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserDTO dto) {
        User user = new User();
        user.setName(dto.name);
        user.setEmail(dto.email);
        user.setRegion(dto.region);

        if (dto.interestIds != null && !dto.interestIds.isEmpty()) {
            List<Interest> interests = interestRepo.findAllById(dto.interestIds);
            user.setInterests(interests);
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    @PutMapping("/users/{id}/interests")
    public ResponseEntity<User> setUserInterests(@PathVariable Long id, @RequestBody List<Long> interestIds) {
        User user = userRepository.findById(id).orElseThrow();
        List<Interest> interests = interestRepo.findAllById(interestIds);
        user.setInterests(interests);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/interests")
    public List<Interest> getAllInterests() {
        return interestRepo.findAll();
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
