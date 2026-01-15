package com.dev.exception;

/**
 * Exception thrown when user credentials are invalid.
 * Maps to HTTP 401 Unauthorized status.
 */
public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException() {
        super("Invalid email or password");
    }
}
