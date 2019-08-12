package com.springtemplate.config.PagConfig;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Qiu Ping
 * @description 分页插件
 * @date 2019/8/11
 */
@Configuration
public class PageConfig {

	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		// paginationInterceptor.setLimit(你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制);
		return paginationInterceptor;
	}

}
