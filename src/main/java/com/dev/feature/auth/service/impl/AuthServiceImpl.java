package com.dev.feature.auth.service.impl;

import com.dev.feature.auth.dto.request.LoginRequest;
import com.dev.feature.auth.dto.request.RefreshTokenRequest;
import com.dev.feature.auth.dto.request.RegisterRequest;
import com.dev.feature.auth.dto.response.AuthResponse;
import com.dev.exception.InvalidRefreshTokenException;
import com.dev.exception.PasswordMismatchException;
import com.dev.exception.UserAlreadyExistsException;
import com.dev.model.User;
import com.dev.feature.auth.repository.UserRepository;
import com.dev.feature.auth.service.AuthService;
import com.dev.feature.auth.service.JwtService;
import com.dev.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final JwtDecoder jwtDecoder;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException();
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(request.getEmail());
        }

        String fullName = request.getLastName() + " " + request.getFirstName();

        User user = User.builder()
                .fullName(fullName.trim())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.FREELANCER)  // Default role for new users
                .isActive(true)
                .build();

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        try {
            Jwt jwt = jwtDecoder.decode(request.getRefreshToken());

            String userId = jwt.getSubject();

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String newAccessToken = jwtService.generateToken(user);

            return AuthResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(request.getRefreshToken())
                    .build();

        } catch (Exception e) {
            throw new InvalidRefreshTokenException();
        }
    }
}