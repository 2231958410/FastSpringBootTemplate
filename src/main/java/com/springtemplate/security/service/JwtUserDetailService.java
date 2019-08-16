package com.springtemplate.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springtemplate.common.ExceptionFilter.BadRequestException;
import com.springtemplate.security.VO.UserVO;
import com.springtemplate.security.security.JwtUser;
import com.springtemplate.system.setting.entity.User;
import com.springtemplate.system.setting.mapper.UserMapper;
import com.springtemplate.system.setting.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Qiu Ping
 * @description 自定义UserDetailsService
 * @date 2019/8/14
 */
@Service
public class JwtUserDetailService implements UserDetailsService {

	@Autowired
	IUserService userService;

	@Autowired
	JwtPromissionService jwtPromissionService;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		UserVO uservo = userService.getUserVOByName(s);
		User user = userService.getOne(new QueryWrapper<User>().eq("username",s));

		if(user == null){
			throw new BadRequestException("账号不存在");
		}else{
			return creatUserDetails(user,uservo);
		}

	}


	public JwtUser creatUserDetails(User user,UserVO uservo){

		return new JwtUser(user,jwtPromissionService.mapToGrantedAuthorities(uservo));

	}


}
