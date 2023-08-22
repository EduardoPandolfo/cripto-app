package com.eduardokp.criptoapp.controllers.application;

import com.eduardokp.criptoapp.dtos.ResponseDTO;
import com.eduardokp.criptoapp.exceptions.PasswordInvalidException;
import com.eduardokp.criptoapp.exceptions.ProductNoQuantityAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationAdviceController {

    @ExceptionHandler(ProductNoQuantityAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<?> handlerNoItemException(ProductNoQuantityAvailableException ex) {
        return new ResponseDTO<>(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<?> handlerValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }

        return new ResponseDTO<>(errors);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDTO<?> handlerUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseDTO<>(ex.getMessage());
    }

    @ExceptionHandler(PasswordInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDTO<?> handlerPasswordInvalidException(PasswordInvalidException ex) {
        return new ResponseDTO<>(ex.getMessage());
    }
}
