package com.miguelfelipedev.conversionmngr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
public class CurrencyConverterResposeDto {
    private LocalDateTime convertionDate;
    private Map<String, Double> convertionRate;
    private Double originalAmount;
    private Double convertedAmount;
}
