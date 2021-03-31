package com.pptopt.books.exception;


import java.io.Serializable;

public class BookNotFoundException extends RuntimeException implements Serializable {
    private long book_id;
    public BookNotFoundException(long book_id) {
        super(String.format("Book not found. Id requested: '%s'", book_id));
    }
}
