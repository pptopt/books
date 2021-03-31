package com.pptopt.books.exception;

import java.io.Serializable;

public class InternalServerException extends RuntimeException implements Serializable {

    public InternalServerException(String message) {
        super(message);
    }


}