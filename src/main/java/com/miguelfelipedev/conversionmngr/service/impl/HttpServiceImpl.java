package com.miguelfelipedev.conversionmngr.service.impl;

import com.miguelfelipedev.conversionmngr.service.IHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpServiceImpl implements IHttpService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> ResponseEntity<T> post(String uri, HttpEntity<?> entity, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> get(String uri, Class<T> responseType) {
        return null;
    }
}
