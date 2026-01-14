package com.dev.freelancer.dto.response;

import com.dev.freelancer.model.Freelancer.Experience;
import com.dev.freelancer.model.Freelancer.Education;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
    private String userId;
    private String fullName;
    private String bio;
    private List<String> skills;
    private List<Experience> experience;
    private List<Education> education;
    private BigDecimal hourlyRate;
    private String avatar;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}