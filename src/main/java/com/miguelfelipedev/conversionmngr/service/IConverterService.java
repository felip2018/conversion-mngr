package com.miguelfelipedev.conversionmngr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miguelfelipedev.conversionmngr.dto.ApiExchangeResponseDto;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterRequestDto;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterResposeDto;
import com.miguelfelipedev.conversionmngr.enums.CurrencyEnum;
import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import org.springframework.http.HttpHeaders;

public interface IConverterService {
    ApiExchangeResponseDto currencyConverter(HttpHeaders headers, CurrencyConverterRequestDto request) throws HttpServiceException, JsonProcessingException;
}
