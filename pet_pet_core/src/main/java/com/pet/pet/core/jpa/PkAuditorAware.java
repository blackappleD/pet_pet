package com.pet.pet.core.jpa;

import com.pet.pet.core.util.AuthUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/1/9 14:25
 */
@Component
public class PkAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of(AuthUtil.getLoginIdDefault("0"));
	}
}
