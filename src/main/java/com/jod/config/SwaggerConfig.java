package com.jod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author jod
 * @date 2019/10/2-21:43
 * swagger配置类
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //配置swagger的docket实例
    @Bean
    public Docket docket(Environment environment){
        //swagger在测试环境中使用,在生产环境中不适用
        Profiles profiles = Profiles.of("pro");
        //获取项目环境
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("jods apis")
                //是否启用swagger
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jod.controller"))
                .build();
    }

    //配置swagger信息 apiinfo
    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("jod","","zhuhu11@hotmail.com");
        return new ApiInfo("jod的swaggerAPI接口",
                "yolo",
                "v1.0",
                "",
                 contact,
                "Apach 2.0",
                "",
                new ArrayList<>()
               );
    }
}
