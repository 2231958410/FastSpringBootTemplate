package com.springtemplate.system.setting.service.impl;

import com.springtemplate.security.VO.RoleVO;
import com.springtemplate.system.setting.entity.Role;
import com.springtemplate.system.setting.mapper.RoleMapper;
import com.springtemplate.system.setting.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	RoleMapper roleMapper;


	@Override
	public RoleVO getRoleVOByRoleIds(String id) {
		return roleMapper.getRoleVOByRoleIds(id);
	}
}
