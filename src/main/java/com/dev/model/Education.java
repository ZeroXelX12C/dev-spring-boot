package com.dev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents education history for a freelancer.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    private String schoolName;
    private String degree;
    private String fieldOfStudy;
}
