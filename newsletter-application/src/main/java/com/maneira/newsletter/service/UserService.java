package com.maneira.newsletter.service;

import com.maneira.newsletter.entities.*;
import com.maneira.newsletter.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private InterestRepository interestRepo;

    public User createUser(User user) {
        // Certifica-se que os interesses são buscados do banco de dados
        if (user.getInterests() != null && !user.getInterests().isEmpty()) {
            List<Long> interestIds = user.getInterests().stream()
                    .map(Interest::getId)
                    .toList();
            List<Interest> interests = interestRepo.findAllById(interestIds);
            user.setInterests(interests);
        }

        return userRepo.save(user);
    }


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public User setUserInterests(Long id, List<Long> interestIds) {
        User user = userRepo.findById(id).orElseThrow();
        List<Interest> interests = interestRepo.findAllById(interestIds);
        user.setInterests(interests);
        return userRepo.save(user);
    }


    public List<Interest> getAllInterests() {
        return interestRepo.findAll();
    }

}