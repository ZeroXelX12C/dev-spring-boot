package com.dev.freelancer.dto.request;

import com.dev.freelancer.model.Freelancer.Experience;
import com.dev.freelancer.model.Freelancer.Education;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

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