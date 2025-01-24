package com.pet.pet.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2024/12/26 11:13
 */
@Data
public class UserResDTO {

	@Schema(description = "用户主键id")
	private String id;

	@Schema(description = "账户名")
	private String userAccount;

	@Schema(description = "用户名称")
	private String userName;

	@Schema(description = "邮箱")
	private String email;


}
