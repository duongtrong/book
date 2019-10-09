package com.example.demo.result;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SuppressWarnings("unchecked")
public class ResponseWrapper<T> extends ResponseEntity<T> {

    public ResponseWrapper( T t, int status ) {
        super( ( T ) new Resultset<>( t, status ), HttpStatus.valueOf(status));
    }
}
