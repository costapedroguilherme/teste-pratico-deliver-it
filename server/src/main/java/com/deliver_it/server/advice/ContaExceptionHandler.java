package com.deliver_it.server.advice;

import com.deliver_it.server.dto.ErrorMessage;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContaExceptionHandler {

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Object> handleInvalidJson(JsonProcessingException e) {
        String msg = "JSON inválido!";
        JsonLocation location = e.getLocation();
        if (location != null) {
            msg += " Erro na linha " + location.getLineNr() + " coluna " + location.getColumnNr();
        }
        return ResponseEntity.status(400).body(new ErrorMessage(msg));
    }
}
