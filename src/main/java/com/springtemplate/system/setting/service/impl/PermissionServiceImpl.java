package com.springtemplate.system.setting.service.impl;

import com.springtemplate.system.setting.entity.Permission;
import com.springtemplate.system.setting.mapper.PermissionMapper;
import com.springtemplate.system.setting.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
