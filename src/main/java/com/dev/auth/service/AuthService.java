package com.dev.auth.service;

import com.dev.auth.dto.request.RefreshTokenRequest;
import com.dev.auth.dto.response.AuthResponse;

import com.dev.auth.dto.request.LoginRequest;
import com.dev.auth.dto.request.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);

    void logout();
}
