package com.appsdeveloperblog.estore.productsservice2.controller;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(value = {CommandExecutionException.class})
    public ResponseEntity<String> handleCommandException(CommandExecutionException commandExecutionException){
        return ResponseEntity.internalServerError().body(commandExecutionException.getMessage());
    }
}
