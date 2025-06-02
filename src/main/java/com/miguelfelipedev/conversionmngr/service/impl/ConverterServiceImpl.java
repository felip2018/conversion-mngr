package com.miguelfelipedev.conversionmngr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguelfelipedev.conversionmngr.dto.ApiExchangeResponseDto;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterRequestDto;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterResposeDto;
import com.miguelfelipedev.conversionmngr.enums.CurrencyEnum;
import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.exception.UnhandledRateException;
import com.miguelfelipedev.conversionmngr.service.IConverterService;
import com.miguelfelipedev.conversionmngr.service.IHttpService;
import com.miguelfelipedev.conversionmngr.utils.Constants;
import com.miguelfelipedev.conversionmngr.utils.Utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConverterServiceImpl implements IConverterService {

    @Autowired
    IHttpService httpService;

    @Value("${api.exchange.uri}")
    private String apiExchangeUri;

    @Autowired
    private Utilities utilities;

    @Override
    public CurrencyConverterResposeDto convertCurrency(HttpHeaders headers, CurrencyConverterRequestDto request)
            throws HttpServiceException, UnhandledRateException {
        CurrencyConverterResposeDto response = new CurrencyConverterResposeDto();
        ApiExchangeResponseDto exchangeRates = getCurrencyRates(headers);
        Double rateFrom = utilities.searchRate(exchangeRates.getRates(), request.getFromCurrency());
        Double rateTo = utilities.searchRate(exchangeRates.getRates(), request.getToCurrency());
        Double amountInEur = request.getAmount() / rateFrom;
        Map<String, Double> convertionRate = new HashMap<>();
        convertionRate.put(request.getToCurrency(), rateTo);
        response.setConvertionDate(LocalDateTime.now());
        response.setOriginalAmount(request.getAmount());
        response.setConvertionRate(convertionRate);
        response.setConvertedAmount(amountInEur * rateTo);
        return response;
    }

    @Override
    public ApiExchangeResponseDto getCurrencyRates(HttpHeaders headers) throws HttpServiceException {
        String apiKey = headers.getFirst(Constants.X_API_KEY);
        String uri = String.format("%s?access_key=%s", apiExchangeUri, apiKey);
        ResponseEntity<ApiExchangeResponseDto> apiResponse = httpService.get(uri, headers, ApiExchangeResponseDto.class);
        return apiResponse.getBody();
    }
}
