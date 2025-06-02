package com.miguelfelipedev.conversionmngr.service.impl;

import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterResposeDto;
import com.miguelfelipedev.conversionmngr.enums.CurrencyEnum;
import com.miguelfelipedev.conversionmngr.service.IConverterService;
import com.miguelfelipedev.conversionmngr.service.IHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConverterServiceImpl implements IConverterService {

    @Autowired
    IHttpService httpService;

    @Value("${api.exchange.uri}")
    private String apiExchangeUri;

    @Override
    public CurrencyConverterResposeDto currencyConverter(Double amount, CurrencyEnum fromCurrency, CurrencyEnum toCurrency) {
        return null;
    }
}
