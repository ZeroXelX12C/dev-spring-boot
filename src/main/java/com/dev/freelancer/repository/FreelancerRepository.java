package com.dev.freelancer.repository;

import com.dev.model.Freelancer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreelancerRepository extends MongoRepository<Freelancer, String> {
    // Tìm kiếm profile dựa trên userId (từ JWT)
    Optional<Freelancer> findByUserId(String userId);
}