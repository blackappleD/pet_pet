package com.pet.pet.core.exception;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/1/8 14:43
 */
public class MerchantException extends ApiMarketException {

	private MerchantException(int code, String message) {
		super(code, message);
	}

	private MerchantException(String message) {
		super(message);
	}

	public static MerchantException merCodeExist() {
		return new MerchantException(PARAM_CODE, "商家编码已存在");
	}

	public static MerchantException notFound() {
		return new MerchantException("商家不存在存在");
	}
}
