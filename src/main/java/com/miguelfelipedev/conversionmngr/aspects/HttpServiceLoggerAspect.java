package com.miguelfelipedev.conversionmngr.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class HttpServiceLoggerAspect {

    private static final Logger logger = LogManager.getLogger(HttpServiceLoggerAspect.class);

    @Before(value = "execution(* com.miguelfelipedev.conversionmngr.service.IHttpService.*(..))")
    public void beforeHttpService(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        logger.info("[LOG] before method `{}` execution at HttpService with args: \n{}", methodName, Arrays.toString(args));
    }

    @Around(value = "execution(* com.miguelfelipedev.conversionmngr.service.IHttpService.*(..))")
    public Object aroundHttpService(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        String methodName = joinPoint.getSignature().getName();
        try {
            result = joinPoint.proceed();
            logger.info("[LOG] method: {} \nresult:\n{}", methodName, result);
        } catch (Exception e) {
            logger.error("[LOG] Error in method: {} - {}", methodName, e.getMessage(), e);
        }
        return result;
    }

    @After(value = "execution(* com.miguelfelipedev.conversionmngr.service.IHttpService.*(..))")
    public void afterHttpService() {
        logger.info("[LOG] after method execution at HttpService");
    }

}


