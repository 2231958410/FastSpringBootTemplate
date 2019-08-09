package com.springtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author Qiu Ping
 * Knowledge:
 * Desc:
 */

@SpringBootApplication
public class SpringTemplateApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringTemplateApplication.class, args);
		System.out.println("SpringBootTemplate启动了！");

	}

}
