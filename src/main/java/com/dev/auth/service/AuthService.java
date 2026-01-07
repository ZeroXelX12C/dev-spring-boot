package com.dev.auth.service;

import com.dev.auth.dto.AuthResponse;
import com.dev.auth.dto.LoginRequest;
import com.dev.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}
