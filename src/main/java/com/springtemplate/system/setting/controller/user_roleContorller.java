package com.springtemplate.system.setting.controller;

import com.springtemplate.system.setting.service.IUserService;
import com.springtemplate.util.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Qiu Ping
 * @description 用户角色分配
 * @date 2019/8/23
 */
@RestController
@RequestMapping("/setting//user_role")
public class user_roleContorller {

	@Autowired
	IUserService iUserService;

	/**
	 * 通过id查询
	 * @param none
	 * @return R
	 */
	@ApiOperation(value = "查询角色和用户关系映射" , response = R.class)
	@GetMapping("/all" )
	public R getById() {
		return new R<>(iUserService.getUserRoleArrayList());
	}






}
