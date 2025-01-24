package com.pet.pet.core.mapper.base;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseAllMapper<PO, ResDTO, CreateDTO, UpdateDTO> {

	ResDTO toDto(PO po);

	List<ResDTO> toDtoList(List<PO> poList);


	@Mapping(target = "id", ignore = true)
	PO fromCreateDTO(CreateDTO dto);

	void fromUpdateDTO(@MappingTarget PO po, UpdateDTO dto);

}