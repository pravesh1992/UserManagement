package com.lodwal.katalyst.controllers;

import com.lodwal.katalyst.annotations.RestApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Test  Controller for test apis")
@RestController
@RequestMapping(path = "/test/sample")
public class TestController {

  @RestApi
  @ApiOperation(value = "This is test api, just to check server is up and running proper", response = String.class, httpMethod = "GET")
  @RequestMapping(method = RequestMethod.GET, path = "/sayHello")
  public String sayHello() {
    return "Hello, How are you ";
  }
}