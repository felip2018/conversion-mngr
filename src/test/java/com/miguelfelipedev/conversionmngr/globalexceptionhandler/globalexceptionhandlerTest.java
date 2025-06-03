package com.miguelfelipedev.conversionmngr.globalexceptionhandler;

import com.miguelfelipedev.conversionmngr.exception.AuthorizationException;
import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.exception.UnhandledRateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class globalexceptionhandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
