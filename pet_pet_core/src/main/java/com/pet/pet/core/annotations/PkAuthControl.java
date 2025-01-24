package com.pet.pet.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chentong
 * @date 2020/10/26 6:21 下午
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PkAuthControl {

    /**
     * 是否必须登录，未登录就抛出错误
     */
    boolean requireLogin() default true;

    /**
     * 是否必须通过权限校验，不通过就抛出错误，权限数据会从数据库中查找
     */
    boolean requirePermission() default true;

}
