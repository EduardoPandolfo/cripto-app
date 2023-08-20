package com.eduardokp.criptoapp.controllers;

import com.eduardokp.criptoapp.dtos.ResponseDTO;
import com.eduardokp.criptoapp.exceptions.ProductNoQuantityAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationAdviceController {

    @ExceptionHandler(ProductNoQuantityAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<?> handlerNoItemException(ProductNoQuantityAvailableException ex) {
        return new ResponseDTO<>(ex.getMessage());
    }
}
