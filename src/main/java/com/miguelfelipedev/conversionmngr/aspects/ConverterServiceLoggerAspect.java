package com.miguelfelipedev.conversionmngr.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ConverterServiceLoggerAspect {

    private static final Logger logger = LogManager.getLogger(ConverterServiceLoggerAspect.class);

    @Before(value = "execution(* com.miguelfelipedev.conversionmngr.service.IConverterService.*(..))")
    public void beforeConverterService(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        logger.info("[LOG] before method execution of `{}` at ConverterService with args: \n{}", methodName, Arrays.toString(args));
    }

    @Around(value = "execution(* com.miguelfelipedev.conversionmngr.service.IConverterService.*(..))")
    public Object aroundConverterService(ProceedingJoinPoint joinPoint) throws Throwable {
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

}
