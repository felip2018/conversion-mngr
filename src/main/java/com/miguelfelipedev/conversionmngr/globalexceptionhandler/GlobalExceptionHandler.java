package com.miguelfelipedev.conversionmngr.globalexceptionhandler;

import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpServiceException.class)
    public ResponseEntity<Map<String, Object>> handleHttpServiceException(HttpServiceException e) {
        Map<String, Object> response = new HashMap<>();
        response.put(Constants.MESSAGE, e.getMessage());
        response.put(Constants.TIMESTAMP, System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
