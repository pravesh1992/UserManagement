package com.lodwal.katalyst.interceptors;

import com.lodwal.katalyst.business.apis.ApplicationTokenService;
import com.lodwal.katalyst.business.constants.CommonHeaderParameterNames;
import com.lodwal.katalyst.business.constants.UserAuthenticationApiNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * This interceptor validate given token id in header and pass request for next processing
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  ApplicationTokenService applicationTokenService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
    if (handler instanceof HandlerMethod) {
      final HandlerMethod handlerMethod = (HandlerMethod) handler;
      Method method = handlerMethod.getMethod();
      if (!UserAuthenticationApiNames.API_NAMES.contains(method.getName())) {
        String tokenId = request.getHeader(CommonHeaderParameterNames.TOKENID);
        this.applicationTokenService.validateApplicationToken(tokenId);
      }
    }
    return true;
  }
}