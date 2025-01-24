package com.pet.pet.core.permstrategy;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmptyDataPermStrategy extends DataPermStrategy {

	@Override
	public <P> List<Predicate> permPredicate(Root<P> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return List.of();
	}

	@Override
	public String getName() {
		return "全部";
	}

	@Override
	public <P> boolean isInPerm(P data) {
		return true;
	}

}
