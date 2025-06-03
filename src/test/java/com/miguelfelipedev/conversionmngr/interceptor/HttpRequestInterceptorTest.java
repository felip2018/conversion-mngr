package com.miguelfelipedev.conversionmngr.interceptor;

import com.miguelfelipedev.conversionmngr.exception.AuthorizationException;
import com.miguelfelipedev.conversionmngr.utils.Utilities;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HttpRequestInterceptorTest {

    @InjectMocks
    private HttpRequestInterceptor interceptor;

    @Mock
    private Utilities utilities;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testPreHandleSuccess() throws Exception {
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/v1/convert-currency"));
        when(request.getHeader(any(String.class))).thenReturn("dummy-header");
        when(utilities.validateApiKey(any(String.class))).thenReturn(true);
        assertTrue(interceptor.preHandle(request, response, new Object()));
    }

    @Test
    public void testPreHandleAuthorizationExceptionByApiKeyNull() throws Exception {
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/v1/convert-currency"));
        when(request.getHeader(any(String.class))).thenReturn(null);
        when(utilities.validateApiKey(any(String.class))).thenReturn(true);
        assertThrows(AuthorizationException.class, () -> interceptor.preHandle(request, response, new Object()));
    }

    @Test
    public void testPreHandleAuthorizationExceptionByApiKeyNotValid() throws Exception {
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/v1/convert-currency"));
        when(request.getHeader(any(String.class))).thenReturn("dummy-header");
        when(utilities.validateApiKey(any(String.class))).thenReturn(false);
        assertThrows(AuthorizationException.class, () -> interceptor.preHandle(request, response, new Object()));
    }
}
