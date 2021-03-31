package com.pptopt.books.exception;

import java.io.Serializable;

public class ValidationException extends RuntimeException implements Serializable {

    public ValidationException(String message) {
        super(message);
    }


}

