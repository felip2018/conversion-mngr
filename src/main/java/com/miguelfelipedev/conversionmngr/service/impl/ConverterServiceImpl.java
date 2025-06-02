package com.miguelfelipedev.conversionmngr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguelfelipedev.conversionmngr.dto.ApiExchangeResponseDto;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterRequestDto;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterResposeDto;
import com.miguelfelipedev.conversionmngr.enums.CurrencyEnum;
import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.service.IConverterService;
import com.miguelfelipedev.conversionmngr.service.IHttpService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConverterServiceImpl implements IConverterService {

    @Autowired
    IHttpService httpService;

    @Value("${api.exchange.uri}")
    private String apiExchangeUri;

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(ConverterServiceImpl.class);

    @Override
    public ApiExchangeResponseDto currencyConverter(HttpHeaders headers, CurrencyConverterRequestDto request) throws HttpServiceException, JsonProcessingException {
        CurrencyConverterResposeDto response = new CurrencyConverterResposeDto();
        ResponseEntity<ApiExchangeResponseDto> apiResponse = httpService.get(apiExchangeUri, headers, ApiExchangeResponseDto.class);
        logger.info("Api response: \n{}", objectMapper.writeValueAsString(apiResponse.getBody()));
        response.setConvertedAmount(15000.0);
        response.setConvertionDate(LocalDateTime.now());
        return apiResponse.getBody();
    }
}
