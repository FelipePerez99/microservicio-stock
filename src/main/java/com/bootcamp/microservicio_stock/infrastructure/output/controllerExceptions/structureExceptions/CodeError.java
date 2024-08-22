package com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.structureExceptions;


import lombok.RequiredArgsConstructor;


public enum CodeError {
    //GENERIC_ERROR("GC-0001", "ERROR GENERICO"),
    ENTITY_ALREADY_EXISTS("GC-0002", "ERROR ENTIDAD YA EXISTE");


    private final String code;
    private final String keyMessage;

    CodeError(String code, String keyMessage) {
        this.code = code;
        this.keyMessage = keyMessage;
    }

    public String getKeyMessage() {
        return keyMessage;
    }

    public String getCode() {
        return code;
    }
}
