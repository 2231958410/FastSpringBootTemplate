package com.springtemplate.security.security;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Qiu Ping
 * @description 认证信息
 * @date 2019/8/14
 */

@Data
public class AuthenticationInfo implements Serializable {

	private final String token;

	private final JwtUser user;
}
