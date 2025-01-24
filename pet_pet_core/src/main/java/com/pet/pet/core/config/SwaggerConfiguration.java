package com.pet.pet.core.config;

import com.pet.pet.core.annotations.PkResponseBody;
import com.pet.pet.core.annotations.PkResponseBodyIgnore;
import com.pet.pet.core.dto.base.BaseResDTO;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.springdoc.core.parsers.ReturnTypeParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Type;

@Configuration
public class SwaggerConfiguration {
	private static final String AUTH_KEY = "JWT Secuity";

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API Market 接口文档")
						.description("API Market 管理后台接口文档")
						.version("1.0.0")
						.contact(new Contact()
								.name("API Market Team")
								.email("support@apimarket.com")))
				.addSecurityItem(new SecurityRequirement().addList(AUTH_KEY))
				.components(new Components().addSecuritySchemes(AUTH_KEY,
						new SecurityScheme().name("jwt").type(SecurityScheme.Type.HTTP).scheme("bearer")
								.bearerFormat("JWT")));
	}

	@Bean
	public ReturnTypeParser returnTypeParser() {
		return new ReturnTypeParser() {
			@Override
			public Type getReturnType(MethodParameter methodParameter) {
				if (methodParameter.getDeclaringClass().isAnnotationPresent(PkResponseBody.class)
						|| methodParameter.getContainingClass().isAnnotationPresent(PkResponseBody.class)
						|| methodParameter.getMethodAnnotation(PkResponseBody.class) != null) {
					if (methodParameter.getMethodAnnotation(PkResponseBodyIgnore.class) == null) {
						// 检查returntype的类型
						var m = methodParameter.getMethod();
						if (m != null) {
							var returnType = m.getGenericReturnType();
							if (Void.TYPE.equals(returnType)) {
								return BaseResDTO.class;
							}
							return TypeUtils.parameterize(BaseResDTO.class, returnType);
						}
					}

				}
				return ReturnTypeParser.super.getReturnType(methodParameter);
			}
		};
	}
}