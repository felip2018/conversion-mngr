package com.miguelfelipedev.conversionmngr.utils;

import com.miguelfelipedev.conversionmngr.enums.CurrencyEnum;
import com.miguelfelipedev.conversionmngr.exception.UnhandledRateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class Utilities {

    private static final Logger logger = LogManager.getLogger(Utilities.class.getName());

    public static Double searchRate(Map<String, Double> rates, String currency) throws UnhandledRateException {
        logger.info("Searching for rate for currency: {} in rates: \n{}", currency, rates);
        if (!rates.containsKey(currency.toString())) {
            throw new UnhandledRateException("Unknow rate");
        }
        return rates.get(currency.toString());
    }

}
