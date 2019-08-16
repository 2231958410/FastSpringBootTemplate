package com.springtemplate.security.Util;

/**
 * @author Qiu Ping
 * @description 安全工具类
 * @date 2019/8/14
 */

import cn.hutool.json.JSONObject;
import com.springtemplate.common.ExceptionFilter.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 获取当前登录的用户
 * @author Zheng Jie
 * @date 2019-01-17
 */
public class SecurityUtils {

	public static UserDetails getUserDetails() {
		UserDetails userDetails = null;
		try {
			userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			throw new BadRequestException(HttpStatus.UNAUTHORIZED, "登录状态过期");
		}
		return userDetails;
	}

	/**
	 * 获取系统用户名称
	 * @return 系统用户名称
	 */
	public static String getUsername(){
		Object obj = getUserDetails();
		return ((UserDetails) obj).getUsername();
	}

	/**
	 * 获取系统用户id
	 * @return 系统用户id
	 */
	public static Long getUserId(){
		Object obj = getUserDetails();
		JSONObject json = new JSONObject(obj);
		return json.get("id", Long.class);
	}
}
