package com.miguelfelipedev.conversionmngr.globalexceptionhandler;

import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.exception.UnhandledRateException;
import com.miguelfelipedev.conversionmngr.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, Object> response = new HashMap<>();
        response.put(Constants.ERROR, "Inputs Validation Failed");
        response.put(Constants.MESSAGE, e.getMessage());
        response.put(Constants.CURRENT_DATE, LocalDateTime.now());

        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpServiceException.class)
    public ResponseEntity<Map<String, Object>> handleHttpServiceException(HttpServiceException e) {
        Map<String, Object> response = new HashMap<>();
        response.put(Constants.ERROR, "Exchange API Error Service");
        response.put(Constants.MESSAGE, e.getMessage());
        response.put(Constants.CURRENT_DATE, LocalDateTime.now());
        logError(response);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UnhandledRateException.class)
    public ResponseEntity<Map<String, Object>> handleUnhandledRateException(UnhandledRateException e) {
        Map<String, Object> response = new HashMap<>();
        response.put(Constants.ERROR, "Convertion Process Error");
        response.put(Constants.MESSAGE, e.getMessage());
        response.put(Constants.CURRENT_DATE, LocalDateTime.now());
        logError(response);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    private void logError(Map<String, Object> response) {
        logger.error("<<<< ERROR: {}", response);
    }
}
