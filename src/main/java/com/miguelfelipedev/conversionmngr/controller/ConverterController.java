package com.miguelfelipedev.conversionmngr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterRequestDto;
import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.service.IConverterService;
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
    public ResponseEntity<Object> convertCurrency(@RequestHeader HttpHeaders headers,
            @RequestBody CurrencyConverterRequestDto requestBody) throws HttpServiceException, JsonProcessingException {
        return ResponseEntity.ok().body(converterService.currencyConverter(headers, requestBody));
    }

}

