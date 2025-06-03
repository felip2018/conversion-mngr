package com.miguelfelipedev.conversionmngr.service;

import com.miguelfelipedev.conversionmngr.dto.ApiExchangeResponseDto;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterRequestDto;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterResposeDto;
import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.exception.UnhandledRateException;
import com.miguelfelipedev.conversionmngr.service.impl.ConverterServiceImpl;
import com.miguelfelipedev.conversionmngr.service.impl.HttpServiceImpl;
import com.miguelfelipedev.conversionmngr.utils.MockedData;
import com.miguelfelipedev.conversionmngr.utils.Utilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ConverterServiceImplTest {

    @InjectMocks
    private ConverterServiceImpl converterService;

    @Mock
    private HttpServiceImpl httpService;

    @Mock
    private Utilities utilities;

    HttpHeaders headers;

    CurrencyConverterRequestDto request;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(converterService, "apiExchangeUri", "http://localhost:3000/v1/latest");

        headers = new HttpHeaders();
        headers.set("x-rquuid", "dummy-uuid");
        headers.set("x-api-key", "dummy-api-key");

        request = new CurrencyConverterRequestDto();
        request.setAmount(15.5);
        request.setFromCurrency("EUR");
        request.setToCurrency("USD");
    }

    @Test
    public void testConvertCurrency() throws HttpServiceException, UnhandledRateException {
        when(httpService.get(any(String.class), any(HttpHeaders.class), any(Class.class)))
                .thenReturn(ResponseEntity.ok(MockedData.makeApiExchangeResponseDto()));
        when(utilities.searchRate(any(HashMap.class), any(String.class)))
                .thenReturn(1.0)
                .thenReturn(1.141989);
        CurrencyConverterResposeDto response = converterService.convertCurrency(headers, request);
        assertNotNull(response);
        System.out.println("Test response -> " + response);
        assertEquals(1.141989, response.getConvertionRate().get("USD"));
        assertEquals(15.5, response.getOriginalAmount());
        assertEquals(17.700829499999998, response.getConvertedAmount());

    }

    @Test
    public void testConvertCurrencyHttpServiceException() throws HttpServiceException {
        when(httpService.get(any(String.class), any(HttpHeaders.class), any(Class.class)))
                .thenThrow(new HttpServiceException("something went wrong"));

        assertThrows(HttpServiceException.class, () -> {
            converterService.convertCurrency(headers, request);
        });
    }

    @Test
    public void testConvertCurrencyUnhandledRateException() throws HttpServiceException, UnhandledRateException {
        when(httpService.get(any(String.class), any(HttpHeaders.class), any(Class.class)))
                .thenReturn(ResponseEntity.ok(MockedData.makeApiExchangeResponseDto()));
        when(utilities.searchRate(any(HashMap.class), any(String.class)))
                .thenThrow(new UnhandledRateException("something went wrong"));
        assertThrows(UnhandledRateException.class, () -> {
            converterService.convertCurrency(headers, request);
        });

    }

}
