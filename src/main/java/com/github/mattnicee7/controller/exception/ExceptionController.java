package com.github.mattnicee7.controller.exception;

import com.github.mattnicee7.exception.CpfInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<String> handleCustomException(CpfInvalidoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

}
