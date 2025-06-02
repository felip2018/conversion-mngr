package com.miguelfelipedev.conversionmngr.service;

import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterResposeDto;
import com.miguelfelipedev.conversionmngr.enums.CurrencyEnum;

public interface IConverterService {
    public CurrencyConverterResposeDto currencyConverter(Double amount, CurrencyEnum fromCurrency, CurrencyEnum toCurrency);
}
