package com.lodwal.katalyst.controllers;

import com.lodwal.katalyst.business.apis.LoginAPI;
import com.lodwal.katalyst.business.objects.LoginResult;
import com.lodwal.katalyst.exception.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/loginInterface")
@Api(description = "This controller class contains rest api related sign-in/sign-out operations")
public class LoginController {

  @Autowired
  private LoginAPI loginAPI;

  @ApiOperation(value = "This is used to login to system", response = LoginResult.class, httpMethod = "POST")
  @RequestMapping(path = "/v1/login", method = RequestMethod.POST)
  public LoginResult login(@NotNull @NotBlank @RequestParam(name = "emailId") final String emailId, @NotNull @NotBlank @RequestParam(name = "password") final String securedPassword) throws ApplicationException {
    return this.loginAPI.login(emailId, securedPassword);
  }
}