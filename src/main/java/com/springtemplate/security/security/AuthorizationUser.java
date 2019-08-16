package com.springtemplate.security.security;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Qiu Ping
 * @description 用户登录信息
 * @date 2019/8/14
 */
@Data
public class AuthorizationUser {
	@NotBlank
	private String username;

	@NotBlank
	private String password;

	private String code;

	private String uuid = "";

	@Override
	public String toString() {
		return "{username=" + username  + ", password= ******}";
	}
}
