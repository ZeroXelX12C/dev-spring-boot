package com.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
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
        SpringApplication app = new SpringApplication(DevApplication.class);
        app.addListeners((ApplicationListener<ApplicationReadyEvent>) event -> {
            Environment env = event.getApplicationContext().getEnvironment();
            String port = env.getProperty("server.port", "8080");
            System.out.println("\n==============================================");
            System.out.println("APPLICATION IS READY!");
            System.out.println("==============================================");
            System.out.println("Port: " + port);
            System.out.println("Profile: " + env.getProperty("spring.profiles.active", "default"));
            System.out.println("MongoDB URI: " + env.getProperty("SPRING_DATA_MONGODB_URI", "Not configured"));
            System.out.println("==============================================");
            System.out.println("API Documentation:");
            System.out.println("  Swagger UI: http://localhost:" + port + "/swagger-ui/index.html");
            System.out.println("  OpenAPI JSON: http://localhost:" + port + "/v3/api-docs");
            System.out.println("==============================================\n");
        });
        app.run(args);
    }
}