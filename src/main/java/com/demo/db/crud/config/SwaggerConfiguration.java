package com.demo.db.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo.db.crud"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Simple CRUD REST API",
                "Spring Boot REST API with Database Integration",
                "1.0",
                "Terms of service",
                new Contact("Amit Ojha", "https://localhost:8080/api/db-demo/employee", "amit.ojha@geminisolutions.com"),
                "Apache License Version 2.0",
                "Apache License", new ArrayList<>());
        return apiInfo;
    }
}
