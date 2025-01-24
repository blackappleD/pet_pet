package com.pet.pet.core.web;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.pet.pet.core.annotations.PkResponseBody;
import com.pet.pet.core.annotations.PkResponseBodyIgnore;
import com.pet.pet.core.dto.base.BaseResDTO;
import com.pet.pet.core.exception.ApiMarketException;
import com.pet.pet.core.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Order(0)
@Slf4j
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
	public boolean isProdOrPre() {
		return "prod".equals(SpringUtil.getActiveProfile()) || "prev".equals(SpringUtil.getActiveProfile()) || "pre".equals(SpringUtil.getActiveProfile());
	}

	private int getCode(Throwable ex) {
		if (ex instanceof NullPointerException) {
			// 空指针错误10001
			return 10001;
		} else if (ex instanceof JpaSystemException) {
			// jpa异常
			return 20000;
		} else if (ex instanceof DataIntegrityViolationException) {
			// 数据违反约束
			return 20001;

		}

		return 10000;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		var method = returnType.getMethod();
		if (method == null) {
			return false;
		}
		// 只针对带有特殊注解的控制器
		return (method.getAnnotation(PkResponseBody.class) != null
				|| returnType.getContainingClass().getAnnotation(PkResponseBody.class) != null
				|| returnType.getDeclaringClass().getAnnotation(PkResponseBody.class) != null)
				&& returnType.getMethodAnnotation(PkResponseBodyIgnore.class) == null;
	}

	@Override
	public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType,
	                              Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
	                              ServerHttpResponse response) {
		if (body instanceof BaseResDTO) {
			return body;
		}
		if (StringHttpMessageConverter.class.isAssignableFrom(selectedConverterType)) {
			return JSONUtil.toJsonStr(BaseResDTO.success(body));
		}
		return BaseResDTO.success(body);
	}

	@ExceptionHandler(ApiMarketException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Object dgExceptionHandler(ApiMarketException ex) {
		if (ex.isLog()) {
			log.error(ex.getMessage(), ex);
		} else {
			log.debug(ex.getMessage(), ex);
		}
		// 非生产环境需要打印堆栈
		return BaseResDTO.fail(ex.getCode(), ex.getMessage(), isProdOrPre() ? null : ex);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object badRequestExceptionHandler(MethodArgumentNotValidException ex) {
		return BaseResDTO.fail(400, ex.getDetailMessageArguments()[1].toString());
	}

	@ExceptionHandler(LoginException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Object loginInvalidExceptionHandler(LoginException ex) {
		if (ex.isLog()) {
			log.error(ex.getMessage(), ex);
		} else {
			log.debug(ex.getMessage(), ex);
		}
		return BaseResDTO.fail(ex.getCode(), ex.getMessage());
	}

//	@ExceptionHandler(Throwable.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public Object exceptionHandler(Throwable ex) {
//		log.error(ex.getMessage(), ex);
//		// 如果非生产环境，则报错显示完整信息，生产环境将常用错误整理为错误码
//		if (isProdOrPre()) {
//			return BaseResponseDTO.fail(DgException.DEFAULT_CODE,
//					String.format("%s【%d】", DgException.DEFAULT_MSG, getCode(ex)));
//		} else {
//			return BaseResponseDTO.fail(DgException.DEFAULT_CODE,
//					String.format("%s【%d】:%s", DgException.DEFAULT_MSG, getCode(ex), ex.getMessage()), ex);
//		}
//	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object maxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException ex) {
		log.error(ex.getMessage(), ex);
		return BaseResDTO.fail(ApiMarketException.DEFAULT_CODE, "文件大小超出限制");
	}
}