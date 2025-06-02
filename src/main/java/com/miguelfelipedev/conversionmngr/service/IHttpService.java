package com.miguelfelipedev.conversionmngr.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface IHttpService {
    <T> ResponseEntity<T> post(String uri, HttpEntity<?> entity, Class<T> responseType);
    <T> ResponseEntity<T> get(String uri, Class<T> responseType);
}
