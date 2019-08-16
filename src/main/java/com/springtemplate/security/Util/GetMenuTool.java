package com.springtemplate.security.Util;

import com.springtemplate.security.VO.RoleVO;
import com.springtemplate.system.setting.DTO.MenuDTO;
import com.springtemplate.system.setting.entity.Role;
import com.springtemplate.system.setting.service.IMenuService;
import com.springtemplate.system.setting.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Qiu Ping
 * @description 获取菜单工具
 * @date 2019/8/16
 */
@Component
public class GetMenuTool {

	@Autowired
	IMenuService menuService;

	public List<MenuDTO> getSetMenuByRole(List<Role> roles){

		List<String> list  = new ArrayList<>();

		for(Role role : roles){
			list.add(role.getId().toString());
			System.out.println("获取的Role_ID为："+role.getId().toString());
		}

		//根据角色获取MenuDTO
		List<MenuDTO> menuDTOS = new ArrayList<>();

		for(int i=0;i<list.size();i++){
			menuDTOS.add(menuService.getMenuDTOById(list.get(i)));
		}

		return menuDTOS;





	}

}
