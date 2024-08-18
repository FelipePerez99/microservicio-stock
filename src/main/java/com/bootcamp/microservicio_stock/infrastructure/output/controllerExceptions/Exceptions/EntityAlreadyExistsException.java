package com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.Exceptions;

import com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.structureExceptions.CodeError;

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
        this.keyMessage = CodeError.ENTIDAD_YA_EXISTE.getKeyMessage();
        this.code = CodeError.ENTIDAD_YA_EXISTE.getCode();
    }
}
