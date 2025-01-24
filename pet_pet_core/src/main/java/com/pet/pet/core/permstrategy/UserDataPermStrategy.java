package com.pet.pet.core.permstrategy;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.pet.pet.core.po.UserPO;
import com.pet.pet.core.util.AdminUserUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.data.util.CastUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Component
public class UserDataPermStrategy extends DataPermStrategy {

	@Override
	public <P> List<Predicate> permPredicate(Root<P> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = ListUtil.list(false);

		var user = AdminUserUtil.getCurrentUser();
		Attribute<?, ?> attr = findAttribute(root.getModel());
		if (attr != null) {
			if (attr.isAssociation() && !attr.isCollection()) {
				// ManyToOne或OneToOne外键关联
				predicates.add(cb.equal(root.get(attr.getName()), user));
			} else if (attr.isAssociation() && attr.isCollection()) {
				// ManyToMany和OneToMany
				predicates.add(cb.isMember(user, root.get(attr.getName())));
			} else if (!attr.isAssociation() && !attr.isCollection()) {
				// 单个id
				predicates.add(root.get(attr.getName()).in(user.getId()));
			} else {
				// 支持用Long类型+数组
			}
		}
		return predicates;
	}

	private <P> Attribute<?, ?> findAttribute(EntityType<P> root) {
		for (var attr : root.getAttributes()) {
			if ("user".equalsIgnoreCase(attr.getName())
					|| "users".equalsIgnoreCase(attr.getName())
					|| "userIds".equalsIgnoreCase(attr.getName())
					|| "userId".equalsIgnoreCase(attr.getName())
					|| "user_id".equalsIgnoreCase(attr.getName())
					|| "user_ids".equalsIgnoreCase(attr.getName())) {
				return attr;
			}
		}
		return null;
	}

	@Override
	public String getName() {
		return "用户本人";
	}

	@Override
	public <P> boolean isInPerm(P data) {
		var user = AdminUserUtil.getCurrentUser();
		if (user == null) {
			return false;
		}
		var model = SpringUtil.getBean(EntityManager.class).getEntityManagerFactory().getMetamodel()
				.entity(data.getClass());
		var attr = findAttribute(model);
		if (attr != null) {
			var member = (Field) attr.getJavaMember();
			try {
				var memeberData = member.get(data);
				return isUserIn(user, memeberData, attr);
			} catch (IllegalArgumentException | IllegalAccessException ex) {
				return false;
			}
		}

		return true;

	}

	private boolean isUserIn(UserPO user, Object users, Attribute<?, ?> attr) {
		if (attr.isAssociation() && !attr.isCollection()) {
			// ManyToOne或OneToOne外键关联，判断用户相等
			UserPO u = ((UserPO) users);
			return u != null && Objects.equals(user.getId(), u.getId());
		} else if (attr.isAssociation() && attr.isCollection()) {
			// ManyToMany和OneToMany
			List<UserPO> d = CastUtils.cast(users);
			return d.stream().map(UserPO::getId).anyMatch(x -> Objects.equals(user.getId(), x));
		} else if (!attr.isAssociation() && !attr.isCollection()) {
			// 单个id
			var d = ((Long) users);
			return d != null && Objects.equals(user.getId(), d);
		} else {
			return false;
		}
	}
}
