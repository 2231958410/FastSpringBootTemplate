package com.springtemplate.system.setting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springtemplate.security.VO.RoleVO;
import com.springtemplate.system.setting.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-15
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	RoleVO getRoleVOByRoleIds(String id);

}
