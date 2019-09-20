package com.lodwal.katalyst.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

  @Bean
  public Docket getDocket() {
    return new Docket(DocumentationType.SWAGGER_2).select()
      .apis(RequestHandlerSelectors.basePackage("com.lodwal.katalyst"))
      .build().apiInfo(getAPIInfo());
  }

  private ApiInfo getAPIInfo() {
    return new ApiInfo(
      "Pravesh Lodwal Test API Reference",
      "Pravesh Lodwal Test API for User Management Project",
      "1.0",
      "Terms of Service",
      new Contact("Pravesh Lodwal Developer", "https://datametica.com", "info@datametica.com"),
      "Pravesh Lodwal Licence Version 1.0",
      "https://www.datametica.com", new ArrayList<>()
    );
  }
}