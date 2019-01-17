package com.example.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfig {

    private Docket simpleDocket(String version, String path) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(version)
                .select()
                .paths(regex(path))
                .build()
                .apiInfo(new ApiInfoBuilder().version(version).build());
    }

    @Bean
    public Docket apiV1() {
        return simpleDocket("v1", "/api/v1.*");
    }

    @Bean
    public Docket apiV2() {
        return simpleDocket("v2", "/api/v2.*");
    }

}