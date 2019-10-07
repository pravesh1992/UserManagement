package com.lodwal.katalyst.interceptors;

import com.lodwal.katalyst.annotations.NotEmpty;
import com.lodwal.katalyst.annotations.NotNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
@Aspect
public class LogInterceptor {

  private final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

  @Around("@annotation(com.lodwal.katalyst.annotations.RestApi)")
  Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
    String contextName = method.getDeclaringClass().getSimpleName() + "::" + method.getName();
    List<?> parameters = Arrays.asList(proceedingJoinPoint.getArgs());
    this.logger.info("REQUEST<" + contextName + "> parameters:{}", getParametersInfo(method, parameters));
    long startTime = System.currentTimeMillis();
    try {
      validateParameters(method, parameters);
      Object returnValue = proceedingJoinPoint.proceed();
      this.logger.info("RESPONSE<" + contextName + ">  successfully invoked, Response:{}", responseString(returnValue));
      return returnValue;
    } catch (Throwable throwable) {
      this.logger.error("EXCEPTION<" + contextName + "> cause :{}, stackTrace:{}", throwable.getMessage(), throwable);
    } finally {
      this.logger.info("Total Time taken <" + contextName + "> in milliSeconds: " + (System.currentTimeMillis() - startTime));
    }
    return proceedingJoinPoint.proceed();
  }

  private void validateParameters(Method method, List<?> parameters) throws Exception {
    Annotation[][] annotations = method.getParameterAnnotations();
    Class<?>[] paramTypes = method.getParameterTypes();
    for (int paramNo = 0; paramNo < annotations.length; paramNo++) {
      Annotation[] parameterAnnotations = annotations[paramNo];
      Object parameterValue = parameters.get(paramNo);
      Class<?> paramType = paramTypes[paramNo];
      for (Annotation annotation : parameterAnnotations) {
        if (annotation instanceof NotNull && parameterValue == null)
          throw new Exception("value can't be null for parameter : " + getParameterName(parameterAnnotations));
        if (annotation instanceof NotEmpty) {
          if (paramType.equals(String.class) && StringUtils.isBlank((String) parameterValue)) {
            throw new Exception("value can't be empty for parameter : " + getParameterName(parameterAnnotations));
          } else if (List.class.isAssignableFrom(paramType) && CollectionUtils.isEmpty((List<?>) parameterValue)) {
            throw new Exception("value can't be empty for parameter : " + getParameterName(parameterAnnotations));
          } else if (Set.class.isAssignableFrom(paramType) && CollectionUtils.isEmpty((Set<?>) parameterValue)) {
            throw new Exception("value can't be empty for parameter : " + getParameterName(parameterAnnotations));
          }
        }
      }
    }
  }

  private String getParametersInfo(Method method, List<?> parametersValues) {
    String result = new String();
    if (!CollectionUtils.isEmpty(parametersValues)) {
      StringBuilder parametersInfo = new StringBuilder();
      Annotation[][] annotations = method.getParameterAnnotations();
      for (int paramNo = 0; paramNo < annotations.length; paramNo++) {
        Object parameterValue = parametersValues.get(paramNo);
        parametersInfo.append(getParameterName(annotations[paramNo]) + "=" + parameterValue.toString() + ",");
      }
      result = parametersInfo.substring(0, parametersInfo.lastIndexOf(",")).toString();
    }
    return result;
  }

  private String getParameterName(Annotation[] parameterAnnotations) {
    String parameterName = "Unknown";
    for (Annotation annotation : parameterAnnotations) {
      if (annotation instanceof PathVariable) {
        parameterName = ((PathVariable) annotation).value();
        if (StringUtils.isEmpty(parameterName))
          parameterName = ((PathVariable) annotation).name();
      } else if (annotation instanceof RequestParam) {
        parameterName = ((RequestParam) annotation).value();
        if (StringUtils.isEmpty(parameterName))
          parameterName = ((RequestParam) annotation).name();
      }
    }
    return parameterName;
  }

  private String responseString(Object object) {
    StringBuilder responseString = new StringBuilder();
    if (object != null) {
      if (object instanceof String) {
        String str = (String) object;
        if (str.length() > 100)
          responseString.append(str.substring(0, 100) + "...");
        else
          responseString.append(str);
      } else if (List.class.isAssignableFrom(object.getClass())) {
        List<?> list = (List<?>) object;
        if (!CollectionUtils.isEmpty(list)) {
          responseString.append("[" + list.get(0));
          if (list.size() > 5) {
            for (int i = 1; i < 5; i++)
              responseString.append(", " + list.get(i));
            responseString.append(", " + (list.size() - 5) + " more elements......");
          } else {
            for (int i = 1; i < list.size(); i++)
              responseString.append(", " + list.get(i));
          }
          responseString.append("]");
        }
      } else if (Set.class.isAssignableFrom(object.getClass())) {
        Set<?> set = (Set<?>) object;
        if (!CollectionUtils.isEmpty(set)) {
          Iterator<?> iterator = set.iterator();
          responseString.append("[" + iterator.next());
          if (set.size() > 5) {
            int counter = 1;
            while (iterator.hasNext()) {
              counter++;
              responseString.append(", " + iterator.next());
              if (counter == 5)
                break;
            }
            responseString.append(", " + (set.size() - 5) + " more elements......");
          } else {
            while (iterator.hasNext()) {
              responseString.append(", " + iterator.next());
            }
          }
          responseString.append("]");
        }
      } else
        responseString.append(object.toString());
    }
    return responseString.toString();
  }
}