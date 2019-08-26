package com.springtemplate.system.setting.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.springtemplate.system.setting.DTO.MenuDTO;
import com.springtemplate.system.setting.entity.Menu;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-15
 */

public interface IMenuService extends IService<Menu> {

	/**
	 * 根据MenuId获取MenuDTO 父节点
	 * @param id
	 * @return
	 */
	MenuDTO getMenuDTOById(String id);

	/**
	 * 根据MenuId获取MenuDTO 子节点
	 * @param id
	 * @return
	 */
	MenuDTO getMenuDTOByIdChi(String id);




	/**
	 * permission tree
	 * @return
	 */
	@Cacheable(key = "'tree'")
	Object getMenuTree(List<Menu> menus);


	/**
	 * build Tree
	 * @param menuDTOS
	 * @return
	 */
	Map buildTree(List<MenuDTO> menuDTOS);

	/**
	 * buildMenus
	 * @param byRoles
	 * @return
	 */
	Object buildMenus(List<MenuDTO> byRoles);

	Menu findOne(Long id);

}
