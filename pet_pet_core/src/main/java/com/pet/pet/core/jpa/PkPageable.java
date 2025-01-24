package com.pet.pet.core.jpa;

import com.pet.pet.core.po.base.BasePO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PkPageable extends PageRequest {
	protected PkPageable(int page, int size, Sort sort) {
		super(page, size, sort);
	}

	public static PkPageable of(int page, int size) {
		return new PkPageable(page, size, Sort.unsorted());
	}

	public static PkPageable ofDefaultSort(int page, int size) {
		return new PkPageable(page, size, Sort.by(Sort.Direction.DESC, BasePO.CommonPO.Fields.createTime));
	}

	public static PkPageable of(int page, int size, Sort sort) {
		return new PkPageable(page, size, sort);
	}

	@Override
	public long getOffset() {
		return (long) (getPageNumber() - 1) * getPageSize();
	}
}
