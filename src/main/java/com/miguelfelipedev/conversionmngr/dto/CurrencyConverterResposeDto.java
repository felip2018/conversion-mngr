package com.miguelfelipedev.conversionmngr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class CurrencyConverterResposeDto {
    private LocalDateTime convertionDate;
    private String convertionRate;
    private Double originalAmount;
    private Double convertedAmount;
}
