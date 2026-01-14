package com.dev.model;

import com.dev.model.enums.Gender;
import com.dev.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "Email là bắt buộc")
    @Email(message = "Email không đúng định dạng")
    private String email;

    private String password;

    @NotBlank(message = "Họ tên là bắt buộc")
    private String fullName;

    private String phone;

    private String address;

    private String avatar;

    private Gender gender;

    private Role role; // ADMIN, CLIENT, FREELANCER

    private boolean isActive = true;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}