package com.springtemplate.system.setting.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springtemplate.system.setting.service.IPermissionService;
import com.springtemplate.system.setting.entity.Permission;
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
@RequestMapping("/setting/Permission" )
public class PermissionController {

    private final  IPermissionService Permissionservice;

    /**
     * 分页查询
     * @param page 分页对象
     * @param Permission 
     * @return
     */
    @ApiOperation(value = "查询分页对象" , response = R.class)
    @ApiImplicitParams({
		    @ApiImplicitParam(name = "page", value = "分页对象", required = true, paramType = "query", dataType = "Page"),
		    @ApiImplicitParam(name = "Permission", value = "", required = true, paramType = "query", dataType = "")
    })
    @GetMapping("/page" )
    public R getPermissionPage(Page page, Permission Permission) {
        return new R<>(Permissionservice.page(page, Wrappers.query(Permission)));
    }

    /**
     * 通过id查询
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "通过主键ID查询" , response = R.class)
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
		return new R<>(Permissionservice.getById(id));
    }

    /**
     * 新增$
     * @param _Permission
     * @return R
     */
    @ApiOperation(value = "新增Permission" , response = R.class)
    @PostMapping
    public R save(@RequestBody Permission _Permission){
		return new R<>(Permissionservice.save(_Permission));
		}

    /**
     * 修改
     * @param _Permission
     * @return R
     */
    @ApiOperation(value = "修改" , response = R.class)
    @PutMapping
    public R updateById(@RequestBody Permission _Permission) {
		return new R<>(Permissionservice.updateById(_Permission));
		}

    /**
     * 通过id删除
     * @param _id 主键
     * @return R
     */
    @ApiOperation(value = "删除" , response = R.class)
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer _id) {
		return new R<>(Permissionservice.removeById(_id));
		}
}
