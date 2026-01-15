package com.dev.exception;

/**
 * Exception thrown when refresh token is invalid or expired.
 * Maps to HTTP 401 Unauthorized status.
 */
public class InvalidRefreshTokenException extends AuthenticationException {
    public InvalidRefreshTokenException(String message) {
        super(message);
    }

    public InvalidRefreshTokenException() {
        super("Invalid or expired refresh token");
    }
}
