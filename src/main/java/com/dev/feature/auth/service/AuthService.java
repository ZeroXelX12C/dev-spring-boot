package com.dev.feature.auth.service;

import com.dev.feature.auth.dto.request.RefreshTokenRequest;
import com.dev.feature.auth.dto.response.AuthResponse;

import com.dev.feature.auth.dto.request.LoginRequest;
import com.dev.feature.auth.dto.request.RegisterRequest;

/**
 * Authentication service interface.
 * Handles user registration, login, and token refresh.
 */
public interface AuthService {

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);
}
