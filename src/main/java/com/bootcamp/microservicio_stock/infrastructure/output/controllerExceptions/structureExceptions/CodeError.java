package com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.structureExceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CodeError {
    ENTIDAD_YA_EXISTE("GC-0002", "ERROR ENTIDAD YA EXISTE");

    private final String code;
    private final String keyMessage;
}
