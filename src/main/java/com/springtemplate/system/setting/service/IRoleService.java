package com.springtemplate.system.setting.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.springtemplate.security.VO.RoleVO;
import com.springtemplate.system.setting.entity.Role;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-15
 */

public interface IRoleService extends IService<Role> {

	RoleVO getRoleVOByRoleIds(String id);

}
