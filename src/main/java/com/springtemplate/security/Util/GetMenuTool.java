package com.springtemplate.security.Util;

import com.springtemplate.security.VO.RoleVO;
import com.springtemplate.system.setting.DTO.MenuDTO;
import com.springtemplate.system.setting.entity.Menu;
import com.springtemplate.system.setting.entity.Role;
import com.springtemplate.system.setting.service.IMenuService;
import com.springtemplate.system.setting.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Qiu Ping
 * @description 获取菜单工具
 * @date 2019/8/16
 */
@Component
public class GetMenuTool {

	@Autowired
	IMenuService menuService;

	@Autowired
	IRoleService iRoleService;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<MenuDTO> getSetMenuByRole(List<Role> roles){

		List<String> list  = new ArrayList<>();

		List<List<String>> menulist = new ArrayList<>();


		//获取到角色id
		list = roles.stream().map(role -> role.getId().toString()).collect(Collectors.toList());

		//获取角色所对应的菜单id
		for(int i=0; i<list.size(); i++){
			menulist.add(this.getMenuDTOByMenuId(list.get(i)));
		}

		//根据角色获取MenuDTO
		List<MenuDTO> menuDTOS = new ArrayList<>();

		for(int i=0; i<menulist.size(); i++){
			for(int n=0; n<menulist.get(i).size(); n++){
				menuDTOS.add(this.ChangeVOtoDTO(menuService.getById(menulist.get(i).get(n))));
			}
		}

		//去空处理
		menuDTOS = menuDTOS.stream().filter(str -> str!=null).collect(Collectors.toList());

		return menuDTOS;

	}

	//根据菜单ID获取对应的MenuDTO
	//用于加载角色菜单权限树
	public List<String> getMenuDTOByMenuId(String meun_id){

		String sql = "select menu_id from roles_menus where role_id = " + meun_id;

		return (List<String>)jdbcTemplate.query(sql, new Object[]{}, new ResultSetExtractor() {
			@Override
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				List<String> list = new ArrayList<>();

				while(rs.next()){
					list.add(rs.getString(1));
				}
				return list;
			}

		});

	}


	//将菜单转化为menuVO
	public MenuDTO ChangeVOtoDTO(Menu str){
		MenuDTO menuDTO = new MenuDTO();
		menuDTO.setId(str.getId());
		menuDTO.setName(str.getName());
		menuDTO.setSort(str.getSort());
		menuDTO.setPath(str.getPath());
		menuDTO.setComponent(str.getComponent());
		menuDTO.setPid(str.getPid());
		menuDTO.setIFrame(str.getIFrame());
		menuDTO.setIcon(str.getIcon());
		menuDTO.setChildren(null);
		return menuDTO;
	}


}
