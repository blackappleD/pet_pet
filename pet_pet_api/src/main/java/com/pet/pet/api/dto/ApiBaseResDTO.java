package com.pet.pet.api.dto;

import lombok.Data;

@Data
public class ApiBaseResDTO<T> {
	private Integer code;

	private String message;


	private T data;

	public static <T> ApiBaseResDTO<T> success(T data) {
		ApiBaseResDTO<T> response = new ApiBaseResDTO<>();
		response.code = 200;
		response.data = data;
		return response;
	}

	public static <T> ApiBaseResDTO<T> fail(Integer code, String msg) {
		return fail(code, msg, null);
	}

	public static <T> ApiBaseResDTO<T> fail(Integer code, String msg, Throwable ex) {
		ApiBaseResDTO<T> response = new ApiBaseResDTO<>();
		response.code = code;
		response.message = msg;
		return response; 
	}
}
