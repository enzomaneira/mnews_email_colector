package com.maneira.newsletter.repositories;

import com.maneira.newsletter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}