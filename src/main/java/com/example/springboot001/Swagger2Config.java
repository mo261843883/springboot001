package com.example.springboot001;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类
 * Created by Wangjf on 2017-12-05.
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    //将扫描包的路径放到配置文件中  好处:开发环境显示接口文档,线上环境隐藏，保护接口信息
    @Value("${basePackage}")
    private String basePackage ;

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
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("接口API文档展示,使用参考地址1 http://blog.didispace.com/springbootswagger2/   地址2 http://blog.csdn.net/jerome_s/article/details/53940863   搭建http://blog.csdn.net/rickiyeat/article/details/72639596")
                .termsOfServiceUrl("https://swagger.io/")
                .contact(new Contact("Wangjf","","261843883@qq.com"))
                .version("1.0")
                .build();
    }
}
