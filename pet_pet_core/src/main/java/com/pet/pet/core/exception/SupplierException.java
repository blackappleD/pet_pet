package com.pet.pet.core.exception;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/1/8 14:43
 */
public class SupplierException extends ApiMarketException {

	private SupplierException(int code, String message) {
		super(code, message);
	}

	public SupplierException(String message) {
		super(message);
	}

	public static SupplierException supplierCodeExist() {
		return new SupplierException(PARAM_CODE, "供应商编码已存在");
	}

	public static SupplierException notFound() {
		return new SupplierException("供应商不存在");
	}

	public static SupplierException duplicateApiBind() {

		return new SupplierException("供应商已经绑定过该Api");
	}

	public static SupplierException notSupportBindThisApi() {

		return new SupplierException("为找到绑定该Api的供应商");
	}
}
