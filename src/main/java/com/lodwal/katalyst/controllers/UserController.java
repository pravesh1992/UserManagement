package com.lodwal.katalyst.controllers;

import com.lodwal.katalyst.annotations.NotNull;
import com.lodwal.katalyst.annotations.RestApi;
import com.lodwal.katalyst.business.apis.UserService;
import com.lodwal.katalyst.business.objects.User;
import com.lodwal.katalyst.exception.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Api(description = "This controller contains all apis related user operations")
@RestController
@RequestMapping("/api/UserController")
public class UserController {

  @Autowired
  UserService userService;

  @RestApi
  @ApiOperation(value = "This is used to register a user to system", response = User.class, httpMethod = "POST")
  @RequestMapping(path = "/v1/register", method = RequestMethod.POST)
  public User register(@NotNull @NotBlank @RequestHeader(name = "tokenId") String tokenId, @RequestBody User user) throws ApplicationException {
    return userService.register(user);
  }
}