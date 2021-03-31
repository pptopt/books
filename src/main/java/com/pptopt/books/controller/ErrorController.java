package com.pptopt.books.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.pptopt.books.exception.InternalServerException;
import com.pptopt.books.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pptopt.books.exception.BookNotFoundException;
import com.pptopt.books.pojo.CustomErrorResponse;


/*
 * Controlador de errores
 * Mapea las excepciones en la aplicacion vs el estatus http que se desee  responder
 * */

@RestControllerAdvice
public class ErrorController {

    private final Logger log = LoggerFactory.getLogger(ErrorController.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({BookNotFoundException.class})
    public ResponseEntity<CustomErrorResponse> handle(BookNotFoundException e) {
        CustomErrorResponse er = customizeError(e, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(er, er.getStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<CustomErrorResponse> handle(ValidationException e) {
        CustomErrorResponse er = customizeError(e, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(er, er.getStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, SQLException.class, NullPointerException.class, IOException.class, ArithmeticException.class})
    public ResponseEntity<CustomErrorResponse> handle(InternalServerException e) {
        CustomErrorResponse er = customizeError(e, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(er, er.getStatus());
    }

    private CustomErrorResponse customizeError(Exception e, HttpStatus httpStatus) {
        log.info(e.toString());
        CustomErrorResponse error = new CustomErrorResponse("" + httpStatus.value(), e.getMessage());
        error.setStatus(httpStatus);
        error.setTimestamp(LocalDateTime.now());
        return error;
    }


}

