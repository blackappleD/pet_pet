package com.pet.pet.core.exception;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/1/9 14:32
 */
public class LoginException extends ApiMarketException {

	public static LoginException loginInvalid() {
		return new LoginException("用户登录失效");
	}

	private LoginException(String message) {
		super(401, message);
	}
}
