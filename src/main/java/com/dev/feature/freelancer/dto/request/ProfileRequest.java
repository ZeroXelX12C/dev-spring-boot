package com.dev.feature.freelancer.dto.request;

import java.math.BigDecimal;
import java.util.List;

import com.dev.model.Education;
import com.dev.model.Experience;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfileRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    private String bio;
    private List<String> skills;
    private List<Experience> experience;
    private List<Education> education;

    @DecimalMin(value = "0.0", inclusive = false, message = "Hourly rate must be greater than 0")
    private BigDecimal hourlyRate;

    private String avatar;
}