package com.dev.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Freelancer profile entity.
 * Contains professional information, skills, experience, and education.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "freelancers")
public class Freelancer {

    @Id
    private String id;

    @Indexed(unique = true)
    private String userId;

    private String bio;

    private List<String> skills;

    private BigDecimal hourlyRate;

    private List<Experience> experience;
    private List<Education> education;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}