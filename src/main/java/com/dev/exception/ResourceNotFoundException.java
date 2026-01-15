package com.dev.exception;

// Class này đại diện cho lỗi "Không tìm thấy dữ liệu"
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}