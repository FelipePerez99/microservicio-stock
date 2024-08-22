package com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.Exceptions;

import com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.structureExceptions.CodeError;
import lombok.Getter;

@Getter
public class EntityAlreadyExistsException extends RuntimeException {
    private final String keyMessage;
    private final String code;

    public EntityAlreadyExistsException(CodeError code) {
        super(code.getCode());
        this.keyMessage = code.getKeyMessage();
        this.code = code.getCode();
    }

    public EntityAlreadyExistsException(final String message) {
        super(message);
        this.keyMessage = CodeError.ENTITY_ALREADY_EXISTS.getKeyMessage();
        this.code = CodeError.ENTITY_ALREADY_EXISTS.getCode();
    }
}
