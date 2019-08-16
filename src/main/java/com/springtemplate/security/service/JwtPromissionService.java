package com.springtemplate.security.service;

import com.springtemplate.security.Util.GetPermissionTool;
import com.springtemplate.security.VO.RoleVO;
import com.springtemplate.security.VO.UserVO;
import com.springtemplate.system.setting.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Qiu Ping
 * @description JwtUser权限
 * @date 2019/8/14
 */
@Service
public class JwtPromissionService {

	@Autowired
	GetPermissionTool getPermissionTool;

	/**
	 * key的名称如有修改，请同步修改 UserServiceImpl 中的 update 方法
	 * @param user
	 * @return
	 */
	public Collection<GrantedAuthority> mapToGrantedAuthorities(UserVO user) {

		//获取所属角色
		List<Role> roles = user.getRoles();

		Set<RoleVO> rolevos = getPermissionTool.getSetPermissions(roles);

		return rolevos.stream().flatMap(role -> role.getPermissions().stream())
				.map(permission -> new SimpleGrantedAuthority(permission.getName()))
				.collect(Collectors.toList());

	}

}
