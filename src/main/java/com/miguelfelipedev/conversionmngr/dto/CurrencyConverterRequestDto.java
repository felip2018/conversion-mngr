package com.miguelfelipedev.conversionmngr.dto;

import com.miguelfelipedev.conversionmngr.enums.CurrencyEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CurrencyConverterRequestDto {
    private CurrencyEnum fromCurrency;
    private CurrencyEnum toCurrency;
    private Double amount;
}
