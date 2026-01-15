package com.dev.exception;

/**
 * Exception thrown when attempting to create a user that already exists.
 * Maps to HTTP 409 Conflict status.
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super("User with email " + email + " already exists");
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
