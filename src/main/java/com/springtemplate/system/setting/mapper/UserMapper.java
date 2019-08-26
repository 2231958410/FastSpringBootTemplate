package com.springtemplate.system.setting.mapper;

import com.springtemplate.security.VO.UserVO;
import com.springtemplate.system.setting.DTO.UserRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import com.springtemplate.system.setting.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

	UserVO getUserVOByName(String username);

	List<UserRoleDTO> getUserRoleArrayList();

}
