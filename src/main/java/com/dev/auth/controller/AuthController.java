package com.dev.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.auth.dto.request.LoginRequest;
import com.dev.auth.dto.request.RefreshTokenRequest;
import com.dev.auth.dto.request.RegisterRequest;
import com.dev.auth.dto.response.AuthResponse;
import com.dev.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Authentication controller.
 * Handles user registration, login, and token refresh endpoints.
 */
@Tag(name = "Authentication", description = "APIs for user authentication and authorization")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
        summary = "Register new user",
        description = "Create a new user account with email and password"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "User registered successfully"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "User already exists",
            content = @Content
        )
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "User login",
        description = "Authenticate user and return JWT tokens"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Login successful",
            content = @Content(schema = @Schema(implementation = AuthResponse.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid credentials",
            content = @Content
        )
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(
        summary = "Refresh access token",
        description = "Get a new access token using refresh token"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Token refreshed successfully",
            content = @Content(schema = @Schema(implementation = AuthResponse.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid refresh token",
            content = @Content
        )
    })
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody @Valid RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }
}