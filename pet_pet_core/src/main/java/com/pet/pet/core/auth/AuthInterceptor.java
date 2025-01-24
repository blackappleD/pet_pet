package com.pet.pet.core.auth;

import com.pet.pet.core.annotations.PkAuthControl;
import com.pet.pet.core.annotations.PkAuthIgnore;
import com.pet.pet.core.annotations.PkMethodAuthControl;
import com.pet.pet.core.exception.LoginException;
import com.pet.pet.core.exception.PermissionDeniedException;
import com.pet.pet.core.util.AuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		AuthUtil.clearThreadCache();
		if (handler instanceof HandlerMethod) {
			AuthUtil.setRequest(request);

			HandlerMethod handlerMethod = (HandlerMethod) handler;
			if (handlerMethod.getMethod().isAnnotationPresent(PkAuthIgnore.class)) {
				// 忽略登录检查
				return true;
			}

			// 检查是否需要登录,需要登录时未登录会抛异常，不需要登录则不检查权限
			if (checkLogin(handlerMethod)) {
				//加载数据权限
				AuthUtil.loadCurrentPermStrategy(request, handlerMethod);
				// 检查权限
				checkPermission(request, handlerMethod);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	                       @Nullable ModelAndView modelAndView) throws Exception {
		try {
			HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		} finally {
			AuthUtil.clearThreadCache();
		}
	}

	private boolean checkLogin(HandlerMethod handler) {
		// 从注解中检查是否需要登录
		boolean requireLogin = getRequireLogin(handler);

		if (requireLogin && !AuthUtil.isLogin()) {
			// 检查是否登录
			throw LoginException.loginInvalid();
		}
		return requireLogin;

	}

	private void checkPermission(HttpServletRequest request, HandlerMethod handler) {
		// 从注解中检查是否满足权限
		var requirePermission = getRequirePermission(handler);
		if (requirePermission) {
			// 检查当前用户的所有权限和接口所需的权限
			Set<String> requestPerms = AuthUtil.getRequestPerms(request, handler);
			Set<String> userPerms = AuthUtil.getUserPerms();
			if (!AuthUtil.checkUrlPerms(requestPerms, userPerms)) {
				throw new PermissionDeniedException();
			}
		}
	}

	private boolean getRequireLogin(HandlerMethod handler) {

		PkMethodAuthControl annotationV3 = getAnnotationV3(handler);
		if (annotationV3 != null) {
			return annotationV3.requireLogin();
		}
		var annotations = getAnnotations(handler);
		for (var annotation : annotations) {
			if (annotation != null && annotation.requireLogin()) {
				return true;
			}
		}
		return false;
	}

	private boolean getRequirePermission(HandlerMethod handler) {

		var annotationsV3 = getAnnotationV3(handler);
		if (annotationsV3 != null) {
			return annotationsV3.requirePermission();
		}
		var annotations = getAnnotations(handler);
		for (var annotation : annotations) {
			if (annotation != null && annotation.requirePermission()) {
				return true;
			}
		}
		return false;
	}

	@Deprecated
	private PkAuthControl[] getAnnotations(HandlerMethod handlerMethod) {
		return new PkAuthControl[]{
				handlerMethod.getMethod().getAnnotation(PkAuthControl.class),
				handlerMethod.getMethod().getDeclaringClass().getAnnotation(PkAuthControl.class),
				handlerMethod.getBeanType().getAnnotation(PkAuthControl.class)
		};
	}

	private PkMethodAuthControl getAnnotationV3(HandlerMethod handlerMethod) {
		return handlerMethod.getMethod().getAnnotation(PkMethodAuthControl.class);
	}
}
