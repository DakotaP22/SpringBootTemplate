package com.dpease.template.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    // #region 400
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        logger.warn(e.getMessage());
        return ResponseEntity.status(404).body(e.getMessage());
    }
    // #endregion

    // #region 500
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleKnownRuntimeException(RuntimeException e) {
        logger.error(e.getMessage(), e.getCause());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleUnknownExceptions(Throwable t) {
        logger.error(t.getMessage(), t.getCause());
        return ResponseEntity.internalServerError().body("An Unknown Exception Occurred");
    }
    // //#endregion
}
