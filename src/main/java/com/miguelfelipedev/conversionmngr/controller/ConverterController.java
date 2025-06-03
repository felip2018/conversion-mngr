package com.miguelfelipedev.conversionmngr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterRequestDto;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterResposeDto;
import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.exception.UnhandledRateException;
import com.miguelfelipedev.conversionmngr.service.IConverterService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1")
public class ConverterController {

    @Autowired
    private IConverterService converterService;

    @PostMapping("/convert-currency")
    @Operation(summary = "Realizar la conversión de un monto desde una moneda de origen hacia una moneda de destino.")
    public ResponseEntity<CurrencyConverterResposeDto> convertCurrency(@RequestHeader HttpHeaders headers,
                                                                       @Valid @RequestBody CurrencyConverterRequestDto requestBody)
            throws HttpServiceException, JsonProcessingException, UnhandledRateException {
        return ResponseEntity.ok().body(converterService.convertCurrency(headers, requestBody));
    }

}

