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
public class UtilitiesLoggerAspect {

    private static final Logger logger = LogManager.getLogger(UtilitiesLoggerAspect.class);

    @Before(value = "execution(* com.miguelfelipedev.conversionmngr.utils.Utilities.*(..))")
    public void beforeUtilities(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        logger.info("[LOG] before method execution of `{}` at Utilities with args: \n{}", methodName, Arrays.toString(args));
    }

    @Around(value = "execution(* com.miguelfelipedev.conversionmngr.utils.Utilities.*(..))")
    public Object aroundUtilities(ProceedingJoinPoint joinPoint) throws Throwable {
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
