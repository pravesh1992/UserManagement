package com.lodwal.katalyst.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This is used to configure custom interceptors,filters and resources for application
 */
public class ApplicationConfigurationAdapter implements WebMvcConfigurer {

  @Autowired
  AuthenticationInterceptor authenticationInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(this.authenticationInterceptor);
  }
}