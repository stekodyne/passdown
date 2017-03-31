package com.stekodyne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
public class PassdownApplication {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return objectMapper;
	}

    @Bean
    public Docket mapApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Map")
                .apiInfo(apiInfo("Passdown Map Endpoints", "Spring-based REST API with a little Swagger!"))
                .select()
                .paths(regex("/map.*"))
                .build();
    }

    @Bean
    public Docket dashboardApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Dashboard")
                .apiInfo(apiInfo("Passdown Dashboard Endpoints", "Spring-based REST API with a little Swagger!"))
                .select()
                .paths(regex("/dashboard.*"))
                .build();
    }

    @Bean
    public Docket caseApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Case")
                .apiInfo(apiInfo("Passdown Case Endpoints", "Spring-based REST API with a little Swagger!"))
                .select()
                .paths(regex("/case.*"))
                .build();
    }

    @Bean
    public Docket authenticationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Authentication")
                .apiInfo(apiInfo("Passdown Authentication Endpoints", "Spring-based REST API with a little Swagger!"))
                .select()
                .paths(regex("/authenication.*"))
                .build();
    }

    private ApiInfo apiInfo(String title, String description) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl("https://opensource.org/licenses/Apache-2.0")
                .contact("Steffen Kory")
                .license("Apache License Version 2.0")
                .licenseUrl("https://opensource.org/licenses/Apache-2.0")
                .version("2.0")
                .build();
    }

	public static void main(String[] args) {
		SpringApplication.run(PassdownApplication.class, args);
	}
}
