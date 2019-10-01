package com.lodwal.katalyst.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogInterceptor {

  @Around("@annotation(com.lodwal.katalyst.annotations.RestApi)")
  Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    Object object = proceedingJoinPoint.proceed();
    return object;
  }
}