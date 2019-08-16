package com.springtemplate.system.setting.service.impl;

import com.springtemplate.security.VO.UserVO;
import com.springtemplate.system.setting.entity.User;
import com.springtemplate.system.setting.mapper.UserMapper;
import com.springtemplate.system.setting.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public UserVO getUserVOByName(String username) {
		return userMapper.getUserVOByName(username);
	}
}
