package com.target.productMongodb.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;


public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}
