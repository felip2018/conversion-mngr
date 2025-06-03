package com.miguelfelipedev.conversionmngr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterRequestDto;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterResposeDto;
import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.exception.UnhandledRateException;
import com.miguelfelipedev.conversionmngr.service.IConverterService;
import com.miguelfelipedev.conversionmngr.utils.MockedData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ConverterControllerTest {

    @InjectMocks
    private ConverterController converterController;

    @Mock
    private IConverterService converterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertCurrency() throws HttpServiceException, UnhandledRateException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rquuid", "dummy-uuid");
        headers.set("x-api-key", "dummy-api-key");

        CurrencyConverterRequestDto request = new CurrencyConverterRequestDto();
        request.setAmount(15.5);
        request.setFromCurrency("EUR");
        request.setToCurrency("USD");

        when(converterService.convertCurrency(any(HttpHeaders.class), any(CurrencyConverterRequestDto.class)))
                .thenReturn(MockedData.makeConverterResponseDto());

        ResponseEntity<CurrencyConverterResposeDto> response = converterController.convertCurrency(headers, request);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(15.5, response.getBody().getOriginalAmount());
        assertEquals(17.720157999999998, response.getBody().getConvertedAmount());
    }

}
