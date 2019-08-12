package com.springtemplate.system.mytest.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springtemplate.system.mytest.service.ITestService;
import com.springtemplate.system.mytest.entity.Test;
import com.springtemplate.util.R;
import lombok.AllArgsConstructor;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * test类 前端控制器
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-12
 */

@RestController
@AllArgsConstructor
@Api(tags = "test类")
@RequestMapping("/mytest/Test")
public class TestController {

	private final ITestService Testservice;

	/**
	 * 分页查询
	 *
	 * @param page 分页对象
	 * @param Test test类
	 * @return
	 */
	@ApiOperation(value = "查询分页对象", response = R.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页对象", required = true, paramType = "query", dataType = "Page"),
			@ApiImplicitParam(name = "Test", value = "test类", required = true, paramType = "query", dataType = "test类")
	})
	@GetMapping("/page")
	public R getTestPage(Page page, Test Test) {
		return new R<>(Testservice.page(page, Wrappers.query(Test)));
	}

	/**
	 * 通过id查询
	 *
	 * @param id 主键id
	 * @return R
	 */
	@ApiOperation(value = "通过主键ID查询", response = R.class)
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Integer id) {
		return new R<>(Testservice.getById(id));
	}

	/**
	 * 新增$test类
	 *
	 * @param Test test类
	 * @return R
	 */
	@ApiOperation(value = "新增Test", response = R.class)
	@PostMapping
	public R save(@RequestBody Test _Test) {
		return new R<>(Testservice.save(_Test));
	}

	/**
	 * 修改test类
	 *
	 * @param Test test类
	 * @return R
	 */
	@ApiOperation(value = "修改test类", response = R.class)
	@PutMapping
	public R updateById(@RequestBody Test _Test) {
		return new R<>(Testservice.updateById(_Test));
	}

	/**
	 * 通过id删除test类
	 *
	 * @param id 主键
	 * @return R
	 */
	@ApiOperation(value = "删除test类", response = R.class)
	@DeleteMapping("/{id}")
	public R removeById(@PathVariable Integer _id) {
		return new R<>(Testservice.removeById(_id));
	}
}
