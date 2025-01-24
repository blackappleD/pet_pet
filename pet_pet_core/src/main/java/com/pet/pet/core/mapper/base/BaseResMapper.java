package com.pet.pet.core.mapper.base;

import java.util.List;

public interface BaseResMapper<PO, ResDTO> {

	ResDTO toDto(PO po);

	List<ResDTO> toDtoList(List<PO> poList);
}