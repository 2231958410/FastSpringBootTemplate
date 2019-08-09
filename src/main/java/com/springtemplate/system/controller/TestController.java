package com.springtemplate.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiu Ping
 * Knowledge:
 * Desc:
 */

@Api(tags = "测试类" , value = "用于测试")
@RestController
@RequestMapping("/test")
public class TestController {


	@ApiOperation(value = "hello测试方法" , notes = "测试方法" , response = String.class)
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")

	@PostMapping("/hello")
	public String hello1(String name){
		return "Hello Test !";
	}


	@ApiOperation(value = "hello测试方法" , notes = "测试方法" , response = String.class)
	@GetMapping("/hello")
	public String hello2(){
		return "Hello Test !";
	}


}
