package com.pet.pet.core.dto.base;

import lombok.Data;

@Data
public class BasePageReqDTO {
	private Integer page = 1;
	private Integer size = 10;
	private String search;
}