package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(ZooException exc) {
        log.error("ZooException: {}", exc.getMessage());
        ZooErrorResponse error = new ZooErrorResponse(
                exc.getMessage(),
                exc.getStatus().value(),
                System.currentTimeMillis()
        );
        return ResponseEntity.status(exc.getStatus()).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(Exception exc) {
        log.error("Exception: {}", exc.getMessage());
        ZooErrorResponse error = new ZooErrorResponse(
                exc.getMessage(),
                500,
                System.currentTimeMillis()
        );
        return ResponseEntity.status(500).body(error);
    }
}
