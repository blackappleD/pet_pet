package com.pet.pet.core.permstrategy;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;

import java.util.List;

/**
 * 基本的数据权限策略接口
 */
@Getter
public abstract class DataPermStrategy {
    public String getId(){
        return this.getClass().getName();
    }

    public abstract String getName();

    public abstract <P> List<Predicate> permPredicate(Root<P> root, CriteriaQuery<?> query, CriteriaBuilder cb);

    public abstract <P> boolean isInPerm(P data);
}
