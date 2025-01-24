package com.pet.pet.core.exception;

public class ApiException extends RuntimeException {
    private ApiException(String message) {
        super(message);
    }

    public static ApiException notFound() {
        return new ApiException("API不存在");
    }

    public static ApiException apiCodeExist() {
        return new ApiException("API编码已存在");
    }

    public static ApiException apiDisabled() {
        return new ApiException("API已禁用");
    }
}