package com.miguelfelipedev.conversionmngr.controller;

import com.miguelfelipedev.conversionmngr.dto.CurrencyConverterRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1")
public class ConverterController {

    @PostMapping("/convert-currency")
    public ResponseEntity<Object> convertCurrency(@RequestBody CurrencyConverterRequestDto requestBody) {
        return ResponseEntity.ok().body("Conversion Successful");
    }

}

