package com.springtemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Qiu Ping
 * Knowledge:
 * Desc:
 */

@SpringBootApplication
@MapperScan("com.springtemplate.system")
public class SpringTemplateApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringTemplateApplication.class, args);
		System.out.println("SpringBootTemplate启动了！");

	}

}
