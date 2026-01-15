package com.dev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents work experience for a freelancer.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    private String position;
    private String company;
    private String years;
}
