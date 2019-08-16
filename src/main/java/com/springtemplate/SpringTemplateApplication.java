package com.springtemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Qiu Ping
 */

@SpringBootApplication
@MapperScan("com.springtemplate.system.setting.mapper")
public class SpringTemplateApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringTemplateApplication.class, args);
		//打印容器中的Bean 查错用
		ApplicationContext ctx = ApplicationContextHolder.getApplicationContext();
		try {
			System.out.println("本机IP：" + InetAddress.getLocalHost().toString());
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
		String[] beans = ctx.getBeanDefinitionNames();
		for (int i = 0; i < beans.length; i++) {
			System.out.println("【" + i + "】: " + beans[i]);
		}
		System.out.println("Spring启动了.....");

	}

}
