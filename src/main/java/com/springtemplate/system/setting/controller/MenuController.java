package com.springtemplate.system.setting.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springtemplate.security.Util.GetMenuTool;
import com.springtemplate.security.Util.SecurityUtils;
import com.springtemplate.security.VO.UserVO;
import com.springtemplate.system.setting.DTO.MenuDTO;
import com.springtemplate.system.setting.entity.Menu;
import com.springtemplate.system.setting.service.IMenuService;
import com.springtemplate.system.setting.service.IRoleService;
import com.springtemplate.system.setting.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zheng Jie
 * @date 2018-12-03
 */
@RestController
@RequestMapping("api")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    GetMenuTool getMenuTool;

    private static final String ENTITY_NAME = "menu";

    /**
     * 构建前端路由所需要的菜单
     * @return
     */
    @GetMapping(value = "/menus/build")
    public ResponseEntity buildMenus(){
        UserVO uservo = userService.getUserVOByName(SecurityUtils.getUsername());
        //获取小的菜单
        List<MenuDTO> menuDTOList = getMenuTool.getSetMenuByRole(uservo.getRoles());
        //构建菜单树
        List<MenuDTO> menuDTOTree = (List<MenuDTO>)menuService.buildTree(menuDTOList).get("content");
        return new ResponseEntity(menuService.buildMenus(menuDTOTree),HttpStatus.OK);
    }

    /**
     * 返回全部的菜单
     * @return
     */
    @GetMapping(value = "/menus/tree")
//    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_CREATE','MENU_EDIT','ROLES_SELECT','ROLES_ALL')")
    public ResponseEntity getMenuTree(){
        return new ResponseEntity(menuService.getMenuTree(menuService.list(new QueryWrapper<Menu>().eq("pid","0"))),HttpStatus.OK);
    }

//    @Log("查询菜单")
//    @GetMapping(value = "/menus")
//    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_SELECT')")
//    public ResponseEntity getMenus(CommonQueryCriteria criteria){
//        List<MenuDTO> menuDTOList = menuService.queryAll(criteria);
//        return new ResponseEntity(menuService.buildTree(menuDTOList),HttpStatus.OK);
//    }

//    @Log("新增菜单")
//    @PostMapping(value = "/menus")
//    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_CREATE')")
//    public ResponseEntity create(@Validated @RequestBody Menu resources){
//        if (resources.getId() != null) {
//            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
//        }
//        return new ResponseEntity(menuService.create(resources),HttpStatus.CREATED);
//    }

//    @Log("修改菜单")
//    @PutMapping(value = "/menus")
//    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_EDIT')")
//    public ResponseEntity update(@Validated(Menu.Update.class) @RequestBody Menu resources){
//        menuService.update(resources);
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }

//    @Log("删除菜单")
//    @DeleteMapping(value = "/menus/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_DELETE')")
//    public ResponseEntity delete(@PathVariable Long id){
//        List<Menu> menuList = menuService.findByPid(id);
//
//        // 特殊情况，对级联删除进行处理
//        for (Menu menu : menuList) {
//            roleService.untiedMenu(menu);
//            menuService.delete(menu.getId());
//        }
//        roleService.untiedMenu(menuService.findOne(id));
//        menuService.delete(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
