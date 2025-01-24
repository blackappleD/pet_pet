package com.pet.pet.core.auth;

import com.pet.pet.core.permstrategy.DataPermStrategy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.web.method.HandlerMethod;

import java.util.Set;

/**
 * 自定义登录逻辑
 */
public interface CustomLogic extends Ordered {


	TokenInfo getTokenInfo();

	/**
	 * 登录
	 */
	TokenInfo login(UserEntity user);

	TokenInfo login(UserEntity user, long expire);

	/**
	 * 会话注销
	 */
	void logout();

	Set<String> getRoles(String loginId);

	Set<String> getRequestPerms(HttpServletRequest request, HandlerMethod handler);

	Set<String> getUserPerms(String loginId);

	boolean checkUrlPerms(Set<String> urlPerms, Set<String> userPerms);

	/**
	 * 获取数据权限
	 *
	 * @return
	 */
	default Set<? extends DataPermStrategy> readPermStrategy(HttpServletRequest request, HandlerMethod handler) {
		return Set.of();
	}
}
