package com.pet.pet.core.exception;

public class PermissionDeniedException extends ApiMarketException {

	public PermissionDeniedException() {
		super(403, "权限不足");
	}

}