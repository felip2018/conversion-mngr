package com.miguelfelipedev.conversionmngr.dto;

import com.miguelfelipedev.conversionmngr.customvalidations.ValidEnum;
import com.miguelfelipedev.conversionmngr.enums.CurrencyEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class CurrencyConverterRequestDto {

    @ValidEnum(enumClass = CurrencyEnum.class, message = "The fromCurrency value is not allowed")
    private String fromCurrency;

    @ValidEnum(enumClass = CurrencyEnum.class, message = "The toCurrency value is not allowed")
    private String toCurrency;

    @NotNull(message = "The amount is mandatory")
    @DecimalMin(value = "1.0", message = "The amount is not allowed, the min value is 1.0")
    private Double amount;
}
