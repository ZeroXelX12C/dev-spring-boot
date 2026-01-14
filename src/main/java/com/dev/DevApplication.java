package com.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class DevApplication {

    public static void main(String[] args) {
        // 1. Load .env
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        // 2. Vòng lặp: Lấy tất cả biến trong .env nạp vào System Property
        for (DotenvEntry entry : dotenv.entries()) {
            System.setProperty(entry.getKey(), entry.getValue());
            System.out.println("✅ Loaded env: " + entry.getKey());
        }

        // 3. Chạy Spring Boot
        SpringApplication.run(DevApplication.class, args);
    }
}