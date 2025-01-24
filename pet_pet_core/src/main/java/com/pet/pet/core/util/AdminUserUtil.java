package com.pet.pet.core.util;

import cn.hutool.extra.spring.SpringUtil;
import com.pet.pet.core.po.UserPO;
import com.pet.pet.core.service.UserService;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2024/12/27 15:23
 */
public class AdminUserUtil {

	public static UserPO getCurrentUser() {

		return SpringUtil.getBean(UserService.class).findById(AuthUtil.getLoginId());
	}
}
