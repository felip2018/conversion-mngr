package com.miguelfelipedev.conversionmngr.interceptor;

import com.miguelfelipedev.conversionmngr.exception.AuthorizationException;
import com.miguelfelipedev.conversionmngr.utils.Constants;
import com.miguelfelipedev.conversionmngr.utils.Utilities;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HttpRequestInterceptor implements HandlerInterceptor {

    @Autowired
    private Utilities utilities;

    private static final Logger logger = LogManager.getLogger(HttpRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("[LOG] preHandle: intercepting request");
        logger.info("[LOG] preHandle: request url: {}", request.getRequestURL());
        String headerApiKey = request.getHeader(Constants.X_API_KEY);
        if (headerApiKey == null) {
            throw new AuthorizationException("Unauthorized: API key was not provided");
        }
        if (!utilities.validateApiKey(headerApiKey)) {
            throw new AuthorizationException("Unauthorized: Invalid API key");
        }
        return true;
    }

}
