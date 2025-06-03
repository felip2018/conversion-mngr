package com.miguelfelipedev.conversionmngr.service;

import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.service.impl.HttpServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class HttpServiceImplTest {

    @InjectMocks
    private HttpServiceImpl httpService;

    @Mock
    private RestTemplate restTemplate;

    private HttpHeaders headers;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("x-rquuid", "dummy-uuid");
        headers.add("x-api-key", "dummy-api-key");
    }

    @Test
    public void testGetSuccess() throws HttpServiceException {
        ResponseEntity<String> mockResponse = new ResponseEntity<>("ok", HttpStatus.OK);
        when(restTemplate.getForEntity(any(String.class), any(Class.class)))
                .thenReturn(mockResponse);
        String uri = "http://localhost:8080/api/getRates";
        ResponseEntity<String> response = httpService.get(uri, headers, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ok", response.getBody());
    }

    @Test
    public void testGetThrowHttpServiceException() throws HttpServiceException {
        when(restTemplate.getForEntity(any(String.class), any(Class.class)))
                .thenThrow(new RuntimeException("something went wrong"));
        String uri = "http://localhost:8080/api/getRates";
        assertThrows(HttpServiceException.class, () -> {
            httpService.get(uri, headers, String.class);
        });
    }

}
