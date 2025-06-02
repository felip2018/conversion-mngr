package com.miguelfelipedev.conversionmngr.utils;

import com.miguelfelipedev.conversionmngr.exception.UnhandledRateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Utilities {

    @Value("${api.key}")
    private String apiKey;

    public Double searchRate(Map<String, Double> rates, String currency) throws UnhandledRateException {
        if (!rates.containsKey(currency.toString())) {
            throw new UnhandledRateException("Unknow rate");
        }
        return rates.get(currency.toString());
    }

    public Boolean validateApiKey(String value) {
        return value.equals(apiKey);
    }

}
