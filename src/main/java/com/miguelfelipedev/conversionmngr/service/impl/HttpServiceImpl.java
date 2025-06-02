package com.miguelfelipedev.conversionmngr.service.impl;

import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import com.miguelfelipedev.conversionmngr.service.IHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class HttpServiceImpl implements IHttpService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> ResponseEntity<T> get(String uri, HttpHeaders headers, Class<T> responseType) throws HttpServiceException {
        try {
            return restTemplate.getForEntity(uri, responseType);
        } catch (Exception e) {
            throw new HttpServiceException(e.getMessage());
        }
    }
}
