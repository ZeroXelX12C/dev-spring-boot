package com.dev.exception;

/**
 * Exception thrown when password confirmation doesn't match.
 * Maps to HTTP 400 Bad Request status.
 */
public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
        super("Passwords do not match");
    }

    public PasswordMismatchException(String message) {
        super(message);
    }
}
