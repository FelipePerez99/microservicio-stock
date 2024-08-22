package com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions;

import com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.Exceptions.EntityAlreadyExistsException;
import com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.structureExceptions.CodeError;
import com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.structureExceptions.ErrorUtils;
import com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.structureExceptions.Error;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class RestApiExceptionHandler {


    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                                                        final EntityAlreadyExistsException ex) {
        final Error error = ErrorUtils
                .crearError(CodeError.ENTITY_ALREADY_EXISTS.getCode(),
                        String.format("%s, %s", CodeError.ENTITY_ALREADY_EXISTS.getKeyMessage(),
                                ex.getMessage()),
                        HttpStatus.NOT_ACCEPTABLE.value());
                error.setUrl(req.getRequestURL().toString());
                error.setMetodo(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }
}
