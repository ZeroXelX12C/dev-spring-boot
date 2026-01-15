package com.dev.feature.freelancer.repository;

import com.dev.model.Freelancer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreelancerRepository extends MongoRepository<Freelancer, String> {
    /**
     * Find freelancer profile by user ID.
     * @param userId User ID from JWT token
     * @return Freelancer profile if exists
     */
    Optional<Freelancer> findByUserId(String userId);
}