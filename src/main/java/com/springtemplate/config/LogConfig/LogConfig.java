package com.springtemplate.config.LogConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Qiu Ping
 * @description 日志配置
 * @date 2019/8/12
 */
@Configuration
public class LogConfig {
	private static final Logger LOG = LoggerFactory.getLogger(LogConfig.class);

	@Bean
	public void logMethod() {
		LOG.info("==========print log==========");
	}
}
