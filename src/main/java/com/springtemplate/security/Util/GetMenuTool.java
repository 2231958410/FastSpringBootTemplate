package com.springtemplate.security.Util;

import com.springtemplate.security.VO.RoleVO;
import com.springtemplate.system.setting.DTO.MenuDTO;
import com.springtemplate.system.setting.entity.Menu;
import com.springtemplate.system.setting.entity.Role;
import com.springtemplate.system.setting.service.IMenuService;
import com.springtemplate.system.setting.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
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

	//集合转


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

	//根据角色ID获取所拥有的菜单ID
	public List<Integer> getMenuidByRoleid(String roleid){

		String sql = "select t_menu_id from tree_role_menu where t_role_id = '"+roleid+"'";

		return (List<Integer>)jdbcTemplate.query(sql, new Object[]{}, new ResultSetExtractor() {
			@Override
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				List<Integer> list = new ArrayList<>();

				while(rs.next()){
					list.add(rs.getInt(1));
				}
				return list;
			}

		});
	}

	//list转String
	public List<Integer> CollectChangStr(List<List<String>> list){

		List<Integer> str = new ArrayList<>();

		for(int i=0;i<list.size(); i++){
			for (int n=0; n< list.get(i).size(); n++){
				str.add(Integer.parseInt(list.get(i).get(n)));
			}
		}

		return str;

	}

	//更新角色菜单
	public boolean updateMenuRole(String roleid,Integer[] checkedmenuids,Integer[] halfmenuids){
		//先删除对应角色的所有菜单权限
		this.deletMenuByRoleid(roleid);

		//删除tree表中角色的所有权限
		this.deletTreeMenuByRoleid(roleid);

		//将选中和半选的menuid组合起来插入库中
		Set<String> set = new HashSet<>();

		for(int i=0;i<checkedmenuids.length; i++) {
			set.add(checkedmenuids[i]+"");
		}

		for(int i=0;i<halfmenuids.length; i++) {
			set.add(halfmenuids[i]+"");
		}

		//统一插入 标准表
		String insertsql = "insert into roles_menus values(?,"+roleid+")";

		//统一插入 tree表
		String treeinsertsql = "insert into tree_role_menu values("+roleid+",?)";

		//正规表
		if(set.size()>0){
			set.stream().forEach(arg -> jdbcTemplate.update(insertsql,new Object[]{arg}));
		}

		//tree表
		if(checkedmenuids.length>0){
			Arrays.stream(checkedmenuids).forEach(arg -> jdbcTemplate.update(treeinsertsql,new Object[]{arg}));
		}

		//结束
		return true;

	}


	public int deletMenuByRoleid(String roleid){
		String sql = "delete from roles_menus where role_id = " + roleid;
		return jdbcTemplate.update(sql);
	}

	public int deletTreeMenuByRoleid(String roleid){
		String sql = "delete from tree_role_menu where t_role_id = " + roleid;
		return jdbcTemplate.update(sql);
	}


	//得到二级菜单id
	public List<String> getParentMenuid_two(String meunidstr){
		//提取出所有父菜单 //二级菜单
		String parentsql_two = "select DISTINCT(pid) from menu where id in ("+meunidstr+") and pid!=0";
		return jdbcTemplate.queryForList(parentsql_two,String.class);
	}


}
