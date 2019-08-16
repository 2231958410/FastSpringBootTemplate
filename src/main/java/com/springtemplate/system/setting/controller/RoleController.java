package com.springtemplate.system.setting.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springtemplate.system.setting.service.IRoleService;
import com.springtemplate.system.setting.entity.Role;
import com.springtemplate.util.R;
import lombok.AllArgsConstructor;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-15
 */

@RestController
@AllArgsConstructor
@Api(tags = "")
@RequestMapping("/setting/Role" )
public class RoleController {

    private final  IRoleService Roleservice;

    /**
     * 分页查询
     * @param page 分页对象
     * @param Role 
     * @return
     */
    @ApiOperation(value = "查询分页对象" , response = R.class)
    @ApiImplicitParams({
		    @ApiImplicitParam(name = "page", value = "分页对象", required = true, paramType = "query", dataType = "Page"),
		    @ApiImplicitParam(name = "Role", value = "", required = true, paramType = "query", dataType = "")
    })
    @GetMapping("/page" )
    public R getRolePage(Page page, Role Role) {
        return new R<>(Roleservice.page(page, Wrappers.query(Role)));
    }

    /**
     * 通过id查询
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "通过主键ID查询" , response = R.class)
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
		return new R<>(Roleservice.getById(id));
    }

    /**
     * 新增$
     * @param Role 
     * @return R
     */
    @ApiOperation(value = "新增Role" , response = R.class)
    @PostMapping
    public R save(@RequestBody Role _Role){
		return new R<>(Roleservice.save(_Role));
		}

    /**
     * 修改
     * @param Role 
     * @return R
     */
    @ApiOperation(value = "修改" , response = R.class)
    @PutMapping
    public R updateById(@RequestBody Role _Role) {
		return new R<>(Roleservice.updateById(_Role));
		}

    /**
     * 通过id删除
     * @param id 主键
     * @return R
     */
    @ApiOperation(value = "删除" , response = R.class)
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer _id) {
		return new R<>(Roleservice.removeById(_id));
		}
}
