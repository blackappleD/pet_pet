package com.pet.pet.api.exception;

import com.pet.pet.core.exception.ApiMarketException;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/1/13 9:13
 */
public class ApiException extends ApiMarketException {

	private ApiException(String message) {
		super(message);
	}

	public static ApiException unknownApiCode() {
		return new ApiException("api未配置");
	}

}
