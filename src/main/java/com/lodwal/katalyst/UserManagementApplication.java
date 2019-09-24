package com.lodwal.katalyst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = "com.lodwal.katalyst")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.lodwal.katalyst.persistence.repository")
@EntityScan(basePackages = "com.lodwal.katalyst.persistence.objects")
@SpringBootApplication
public class UserManagementApplication {
  public static void main(String[] args) {
    SpringApplication.run(UserManagementApplication.class, args);
  }
}