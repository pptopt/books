package com.pptopt.books.controller;

import com.pptopt.books.exception.InternalServerException;
import com.pptopt.books.exception.ValidationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ErrorCheckerService {
    @GetMapping("/errorCheckerService")
    public String generator() {
        int i = 9/0;
        throw new InternalServerException("Excepcion de null");
    }
}