package com.springtemplate.config.Druid;

/**
 * @author Qiu Ping
 * @description 监控过滤器
 * @date 2019/8/9
 */


import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
		initParams={
				@WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
		}
)
public class DruidStatFilter extends WebStatFilter {

}
