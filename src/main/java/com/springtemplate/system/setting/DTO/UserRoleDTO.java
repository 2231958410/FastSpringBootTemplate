package com.springtemplate.system.setting.DTO;

import lombok.Data;

/**
 * @author Qiu Ping
 * @description 用户角色
 * @date 2019/8/23
 */
@Data
public class UserRoleDTO {

	//用户ID
	private Long id;

	//用户账户
	private String username;

	//用户角色
	private String name;

	//角色说明
	private String remark;

}
