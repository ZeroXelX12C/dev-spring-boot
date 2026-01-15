package com.dev.exception;

/**
 * Base exception for authentication-related errors.
 * Maps to HTTP 401 Unauthorized status.
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
