package com.example.building.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.building.common.utils.Constants;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configures connection to Swagger.
 *
 * @since 1.0.0
 */
@Configuration
@Profile({"local"})
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Defines the Docket bean for Spring Boot API.
     * Now you can test it in your browser by visiting: ../{context-path}/swagger-ui.html
     *
     * @return the docket bean.
     * @author vn03620
     */
    @Bean(name = "swaggerAPI")
    public Docket swaggerAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInformation())
                .select()
                .apis(RequestHandlerSelectors.basePackage(Constants.BASE_PACKAGE_CONTROLLER))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Defines the Api information.
     *
     * @return the Api Info
     * @author vn03620
     */
    private ApiInfo getApiInformation() {
        return new ApiInfo(
                "Spring Boot API Documentation",
                "API Documentation.",
                "1.0",
                "Terms of service",
                new Contact("Spring Boot Team", "https://localhost:8080", "none"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
