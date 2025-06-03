package com.miguelfelipedev.conversionmngr.globalexceptionhandler;

import com.miguelfelipedev.conversionmngr.exception.AuthorizationException;
import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.exception.UnhandledRateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class globalexceptionhandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleValidationException() {
        FieldError fieldError1 = new FieldError("field", "amount", "The amount is mandatory");
        FieldError fieldError2 = new FieldError("field", "amount", "The amount is not allowed, the min value is 1.0");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));

        MethodParameter methodParameter = mock(MethodParameter.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(methodParameter, bindingResult);

        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleValidationException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Inputs Validation Failed",  response.getBody().get("error"));
        List<String> errors = (List<String>) response.getBody().get("errors");
        System.out.println(errors);
        assertTrue(errors.contains("amount: The amount is mandatory"));
        assertTrue(errors.contains("amount: The amount is not allowed, the min value is 1.0"));
    }

    @Test
    public void testHandleHttpServiceException() {
        HttpServiceException httpServiceException = new HttpServiceException("Something went wrong");
        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleHttpServiceException(httpServiceException);
        assertEquals(409, responseEntity.getStatusCode().value());
        assertNotNull(responseEntity.getBody());
        assertEquals("Something went wrong", responseEntity.getBody().get("message"));
        assertEquals("Exchange API Error Service", responseEntity.getBody().get("error"));
    }

    @Test
    public void testHandleUnhandledRateException() {
        UnhandledRateException unhandledRateException = new UnhandledRateException("Something went wrong");
        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleUnhandledRateException(unhandledRateException);
        assertEquals(409, responseEntity.getStatusCode().value());
        assertNotNull(responseEntity.getBody());
        assertEquals("Something went wrong", responseEntity.getBody().get("message"));
        assertEquals("Convertion Process Error", responseEntity.getBody().get("error"));
    }

    @Test
    public void testHandleAuthorizationException() {
        AuthorizationException authorizationException = new AuthorizationException("Something went wrong");
        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleAuthorizationException(authorizationException);
        assertEquals(401, responseEntity.getStatusCode().value());
        assertNotNull(responseEntity.getBody());
        assertEquals("Something went wrong", responseEntity.getBody().get("message"));
        assertEquals("Authorization Error", responseEntity.getBody().get("error"));
    }

}
