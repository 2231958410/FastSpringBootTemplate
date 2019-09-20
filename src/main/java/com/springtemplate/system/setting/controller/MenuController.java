package com.springtemplate.system.setting.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springtemplate.security.Util.GetMenuTool;
import com.springtemplate.security.Util.SecurityUtils;
import com.springtemplate.security.VO.UserVO;
import com.springtemplate.system.setting.DTO.MenuDTO;
import com.springtemplate.system.setting.entity.Menu;
import com.springtemplate.system.setting.service.IMenuService;
import com.springtemplate.system.setting.service.IRoleService;
import com.springtemplate.system.setting.service.IUserService;
import com.springtemplate.util.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zheng Jie
 * @date 2018-12-03
 */
@RestController
@RequestMapping("/setting/menus")
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
    @GetMapping(value = "/build")
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
    @GetMapping(value = "/tree")
//    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_CREATE','MENU_EDIT','ROLES_SELECT','ROLES_ALL')")
    public ResponseEntity getMenuTree(){
        return new ResponseEntity(menuService.getMenuTree(menuService.list(new QueryWrapper<Menu>().eq("pid","0"))),HttpStatus.OK);
    }

    /**
     * 返回角色所属菜单id
     * @return
     */
    @GetMapping(value = "/getmenubyid/{id}")
    public R getMenuTree(@PathVariable("id") String id){
        return new R<>(getMenuTool.getMenuidByRoleid(id));
    }


    /**
     * 更新角色菜单权限
     *
     */
    @PutMapping(value = "/updateMenuRole")
    public R<Object> updateMenuRole(String roleid, Integer[] checkedmenuids,Integer[] halfmenuids){
        if(getMenuTool.updateMenuRole(roleid,checkedmenuids,halfmenuids)){
            return  R.ok();
        }

        return R.failed();

    }


    /**
     * 分页查询
     * @param page 分页对象
     * @param menu
     * @return
     */
    @ApiOperation(value = "查询分页对象" , response = R.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页对象", required = true, paramType = "query", dataType = "Page"),
            @ApiImplicitParam(name = "Role", value = "", required = true, paramType = "query", dataType = "")
    })
    @GetMapping("/page" )
    public R getRolePage(Page page, Menu menu) {
        return new R<>(menuService.page(page, Wrappers.query(menu)));
    }


    /**
     * 通过id查询
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "通过主键ID查询" , response = R.class)
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return new R<>(menuService.getById(id));
    }

    /**
     * 新增$
     * @param menu
     * @return R
     */
    @ApiOperation(value = "新增User" , response = R.class)
    @PostMapping
    public R save(@RequestBody Menu menu){
        return new R<>(menuService.save(menu));
    }

    /**
     * 修改
     * @param menu
     * @return R
     */
    @ApiOperation(value = "修改" , response = R.class)
    @PutMapping
    public R updateById(@RequestBody Menu menu) {
        return new R<>(menuService.updateById(menu));
    }

    /**
     * 通过id删除
     * @param id 主键
     * @return R
     */
    @ApiOperation(value = "删除" , response = R.class)
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable("id") Integer id) {
        return new R<>(menuService.removeById(id));
    }



}
