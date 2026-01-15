package com.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * Main application class for FreelancerUp.
 * Loads environment variables from .env file before starting Spring Boot.
 */
@SpringBootApplication
@EnableMongoAuditing
public class DevApplication {

    public static void main(String[] args) {
        // Load .env file
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        // Load all environment variables into System properties
        for (DotenvEntry entry : dotenv.entries()) {
            System.setProperty(entry.getKey(), entry.getValue());
            System.out.println("Loaded environment variable: " + entry.getKey());
        }

        // Start Spring Boot application
        SpringApplication.run(DevApplication.class, args);
    }
}