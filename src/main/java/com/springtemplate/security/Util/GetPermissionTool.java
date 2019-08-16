package com.springtemplate.security.Util;

import com.springtemplate.security.VO.RoleVO;
import com.springtemplate.system.setting.entity.Role;
import com.springtemplate.system.setting.mapper.RoleMapper;
import com.springtemplate.system.setting.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Qiu Ping
 * @description 获取权限工具
 * @date 2019/8/15
 */
@Component
public class GetPermissionTool {

	@Autowired
	IRoleService roleService;

	public Set<RoleVO> getSetPermissions(List<Role> roles){

		List<String> list  = new ArrayList<>();
		Set<RoleVO> set = new HashSet<>();

		for(Role role : roles){
			list.add(role.getId().toString());
		}

		for(int i=0; i< list.size();i++){
			set.add(roleService.getRoleVOByRoleIds(list.get(i)));
		}

		return set;
	}

}
