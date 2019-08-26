package com.springtemplate.system.setting.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.springtemplate.security.VO.UserVO;
import com.springtemplate.system.setting.DTO.UserRoleDTO;
import com.springtemplate.system.setting.entity.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-15
 */

public interface IUserService extends IService<User> {

	UserVO getUserVOByName(String username);

	List<UserRoleDTO> getUserRoleArrayList();

}
