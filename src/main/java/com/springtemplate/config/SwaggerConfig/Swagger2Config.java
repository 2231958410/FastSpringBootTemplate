

package com.springtemplate.config.SwaggerConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2全局配置
 * @author pkcxs
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 标题
     */
    @Value("${swagger.title}")
    private String title;

    /**
     * 基本包
     */
    @Value("${swagger.base.package}")
    private String basePackage;

    /**
     * 描述
     */
    @Value("${swagger.description}")
    private String description;

    /**
     * URL
     */
    @Value("${swagger.url}")
    private String url;

    /**
     * 作者
     */
    @Value("${swagger.contact.name}")
    private String contactName;

    /**
     * 作者网址
     */
    @Value("${swagger.contact.url}")
    private String contactUrl;

    /**
     * 作者邮箱
     */
    @Value("${swagger.contact.email}")
    private String contactEmail;

    /**
     * 版本
     */
    @Value("${swagger.version}")
    private String version;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(url)
                .contact(new Contact(contactName,contactUrl,contactEmail))
                .version(version)
                .build();
    }
}
