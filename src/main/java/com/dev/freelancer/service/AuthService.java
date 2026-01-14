package com.dev.freelancer.service;

import com.dev.freelancer.dto.request.RefreshTokenRequest;
import com.dev.freelancer.dto.response.AuthResponse;
import org.springframework.http.ResponseEntity;

import com.dev.freelancer.dto.request.LoginRequest;
import com.dev.freelancer.dto.request.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);

    void logout();
}
