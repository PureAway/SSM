package com.zcy.ssm.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zcy on 2016/9/20.
 */
@EnableWebMvc
@Configuration
@EnableSwagger2
@ComponentScan("com.zcy.ssm.controller")
public class SwaggerConfig {

    @Bean
    public Docket configSpringfoxDocketForAll() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json;charset=UTF-8", "application/xml;charset=UTF-8"))
                .consumes(Sets.newHashSet("application/json;charset=UTF-8", "application/xml;charset=UTF-8"))
                .protocols(Sets.newHashSet("http"/*, "https"*/))
                .forCodeGeneration(true)
                .useDefaultResponseMessages(false)
//                .select().paths(regex(".*"))
//                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "API接口文档",
                "使用Swagger UI构建SpringMVC REST风格的可视化文档",
                "1.0.0",
                "",
                "zcy",
                "Apache License",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );
        return apiInfo;
    }

}
