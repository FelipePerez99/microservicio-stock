package com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.structureExceptions;

public final class ErrorUtils {
    ErrorUtils() {

    }

    /**
     * Crea un nuevo objeto de <code>Error</code>
     *
     * @param codeError
     * @param keyMessage
     * @param codeHttp
     * @return - Objeto creado
     */
    public static Error crearError(final String codeError, final String keyMessage, final Integer codeHttp) {
        final Error error = new Error();
        error.setCodeError(codeError);
        error.setMessage(keyMessage);
        error.setCodeHttp(codeHttp);
        return error;
    }
}
