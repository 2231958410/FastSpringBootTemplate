package com.springtemplate.system.setting.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.springtemplate.system.setting.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-15
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
