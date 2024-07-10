package me.dio.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import me.dio.service.exception.BusinessException;
import me.dio.service.exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseStatusException> handleBusinessException(BusinessException ex) {
        var error = new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseStatusException> handleNoContentException() {
        var errorResponse = new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource ID not found.");

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseStatusException> handleUnexpectedException(Throwable unexpectedException) {
        String message = "Unexpected server error.";
        LOGGER.error(message, unexpectedException);

        var error = new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
