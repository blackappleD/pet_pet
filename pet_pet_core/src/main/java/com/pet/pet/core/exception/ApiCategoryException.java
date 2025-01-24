package com.pet.pet.core.exception;

public class ApiCategoryException extends RuntimeException {
    private ApiCategoryException(String message) {
        super(message);
    }

    public static ApiCategoryException notFound() {
        return new ApiCategoryException("API分类不存在");
    }

}