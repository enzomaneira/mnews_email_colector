package com.maneira.newsletter.resources;

import com.maneira.newsletter.entities.*;
import com.maneira.newsletter.repositories.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
class InterestController {

    @Autowired
    private InterestRepository interestRepository;

    @PostMapping
    public Interest createInterest(@RequestBody Interest interest) {
        return interestRepository.save(interest);
    }

    @GetMapping
    public List<Interest> getAllInterests() {
        return interestRepository.findAll();
    }
}
