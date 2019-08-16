package com.springtemplate.security.controller;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.springtemplate.common.ExceptionFilter.BadRequestException;
import com.springtemplate.common.VO.ImgResultVo;
import com.springtemplate.common.redis.RedisServiceImpl;
import com.springtemplate.security.Util.*;
import com.springtemplate.security.security.AuthenticationInfo;
import com.springtemplate.security.security.AuthorizationUser;
import com.springtemplate.security.security.JwtUser;
import com.springtemplate.system.setting.mapper.UserMapper;
import com.springtemplate.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Qiu Ping
 * @description 安全Controller
 * @date 2019/8/14
 */
@RestController
@RequestMapping("auth")
public class SecurityController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CodeUtil codeUtil;

	@Autowired
	private RedisServiceImpl redisService;

	@Autowired
	@Qualifier("jwtUserDetailService")
	private UserDetailsService userDetailsService;

	@Autowired
	private UserMapper userMapper;

	/**
	 * 登录授权
	 * @param authorizationUser
	 * @return
	 */
	@PostMapping(value = "${jwt.auth.path}")
	public ResponseEntity login(@Validated @RequestBody AuthorizationUser authorizationUser){

		// 查询验证码
		String code = redisService.getCodeVal(authorizationUser.getUuid());
		// 清除验证码
		redisService.delete(authorizationUser.getUuid());
		if (StringUtils.isBlank(code)) {
			throw new BadRequestException("验证码已过期");
		}
		if (StringUtils.isBlank(authorizationUser.getCode()) || !authorizationUser.getCode().equalsIgnoreCase(code)) {
			throw new BadRequestException("验证码错误");
		}
		final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(authorizationUser.getUsername());

		if(!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(authorizationUser.getPassword()))){
			throw new AccountExpiredException("密码错误");
		}

		if(!jwtUser.isEnabled()){
			throw new AccountExpiredException("账号已停用，请联系管理员");
		}

		// 生成令牌
		final String token = jwtTokenUtil.generateToken(jwtUser);

		// 返回 token
		return ResponseEntity.ok(new AuthenticationInfo(token,jwtUser));
	}

	/**
	 * 获取用户信息
	 * @return
	 */
	@GetMapping(value = "${jwt.auth.account}")
	public ResponseEntity getUserInfo(){
		JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
		return ResponseEntity.ok(jwtUser);
	}

	/**
	 * 获取验证码
	 */
	@GetMapping(value = "vCode")
	public ImgResultVo getCode() throws IOException {
		//生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		String uuid = IdUtil.simpleUUID();
		redisService.saveCode(uuid,verifyCode);
		// 生成图片
		int w = 111, h = 36;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
		try {
			return new ImgResultVo(Base64.encode(stream.toByteArray()),uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			stream.close();
		}
	}

}
