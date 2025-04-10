package com.maneira.newsletter.repositories;

import com.maneira.newsletter.entities.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {}