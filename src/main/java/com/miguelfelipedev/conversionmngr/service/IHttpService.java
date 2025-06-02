package com.miguelfelipedev.conversionmngr.service;

import com.miguelfelipedev.conversionmngr.exception.HttpServiceException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface IHttpService {
    <T> ResponseEntity<T> get(String uri, HttpHeaders headers, Class<T> responseType) throws HttpServiceException;
}
