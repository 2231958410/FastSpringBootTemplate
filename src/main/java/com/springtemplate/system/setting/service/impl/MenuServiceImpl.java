package com.springtemplate.system.setting.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springtemplate.system.setting.DTO.MenuDTO;
import com.springtemplate.system.setting.VO.MenuMetaVo;
import com.springtemplate.system.setting.VO.MenuVo;
import com.springtemplate.system.setting.entity.Menu;
import com.springtemplate.system.setting.mapper.MenuMapper;
import com.springtemplate.system.setting.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	@Autowired
	MenuMapper menuMapper;


	@Override
	public MenuDTO getMenuDTOById(String id) {
		return menuMapper.getMenuDTOById(id);
	}

	@Override
	public MenuDTO getMenuDTOByIdChi(String id) {
		return menuMapper.getMenuDTOByIdChi(id);
	}

	@Override
	public Object getMenuTree(List<Menu> menus) {
		List<Map<String,Object>> list = new LinkedList<>();
		menus.forEach(menu -> {
					if (menu!=null){
						List<Menu> menuList = menuMapper.selectList(new QueryWrapper<Menu>().eq("pid",menu.getId()));
						Map<String,Object> map = new HashMap<>();
						map.put("id",menu.getId());
						map.put("label",menu.getName());
						if(menuList!=null && menuList.size()!=0){
							map.put("children",getMenuTree(menuList));
						}
						list.add(map);
					}
				}
		);
		return list;
	}

	@Override
	public Map buildTree(List<MenuDTO> menuDTOS) {
		List<MenuDTO> trees = new ArrayList<MenuDTO>();

		System.out.println(menuDTOS.size());

		for (MenuDTO menuDTO : menuDTOS) {

			if ("0".equals(menuDTO.getPid().toString())) {
				trees.add(menuDTO);
			}

			for (MenuDTO it : menuDTOS) {
				if(it == null ){
					continue;
				}
				if (it.getPid().equals(menuDTO.getId())) {
					if (menuDTO.getChildren() == null) {
						menuDTO.setChildren(new ArrayList<MenuDTO>());
					}
					menuDTO.getChildren().add(it);
				}
			}
		}
		Map map = new HashMap();
		map.put("content",trees.size() == 0?menuDTOS:trees);
		map.put("totalElements",menuDTOS!=null?menuDTOS.size():0);
		return map;
	}

	@Override
	public List<MenuVo> buildMenus(List<MenuDTO> menuDTOS) {
		List<MenuVo> list = new LinkedList<>();
		menuDTOS.forEach(menuDTO -> {
					if (menuDTO!=null){
						List<MenuDTO> menuDTOList = menuDTO.getChildren();
						MenuVo menuVo = new MenuVo();
						menuVo.setName(menuDTO.getName());
						menuVo.setPath(menuDTO.getPath());

						// 如果不是外链
						if(!menuDTO.getIFrame()){
							if(menuDTO.getPid().equals(0L)){
								//一级目录需要加斜杠，不然访问 会跳转404页面
								menuVo.setPath("/" + menuDTO.getPath());
								menuVo.setComponent(StrUtil.isEmpty(menuDTO.getComponent())?"Layout":menuDTO.getComponent());
							}else if(!StrUtil.isEmpty(menuDTO.getComponent())){
								menuVo.setComponent(menuDTO.getComponent());
							}
						}
						menuVo.setMeta(new MenuMetaVo(menuDTO.getName(),menuDTO.getIcon()));
						if(menuDTOList!=null && menuDTOList.size()!=0){
							menuVo.setAlwaysShow(true);
							menuVo.setRedirect("noredirect");
							menuVo.setChildren(buildMenus(menuDTOList));
							// 处理是一级菜单并且没有子菜单的情况
						} else if(menuDTO.getPid().equals(0L)){
							MenuVo menuVo1 = new MenuVo();
							menuVo1.setMeta(menuVo.getMeta());
							// 非外链
							if(!menuDTO.getIFrame()){
								menuVo1.setPath("index");
								menuVo1.setName(menuVo.getName());
								menuVo1.setComponent(menuVo.getComponent());
							} else {
								menuVo1.setPath(menuDTO.getPath());
							}
							menuVo.setName(null);
							menuVo.setMeta(null);
							menuVo.setComponent("Layout");
							List<MenuVo> list1 = new ArrayList<MenuVo>();
							list1.add(menuVo1);
							menuVo.setChildren(list1);
						}
						list.add(menuVo);
					}
				}
		);
		return list;
	}

	@Override
	public Menu findOne(Long id) {
		return null;
	}
}
