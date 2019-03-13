package com.hs.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * TODO: Swagger2配置类: 指定扫描的包路径，Swagger会扫描该包下所有的Controller定义的API，并产生文档内容
 *
 * @author 83998
 * @date 2019/3/4 17:43
 */

@Configuration //spring加载配置类
@EnableSwagger2 //启动swagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //创建该API的基本信息（这些基本信息会展现在文档页面中）
                .apiInfo(apiInfo())
                //函数返回一个 ApiSelectorBuilder 实例用来控制哪些接口暴露给Swagger来展现
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hs.blog"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("个人博客RESTful APIs")
                .description("原文地址链接：http://humblesong.cf/forums")
                .contact("@hUmBlesOng")
                .version("1.0")
                .build();
    }
}
