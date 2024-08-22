package com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.structureExceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    /**
     * Código de error de la aplicación
     */
    private String codeError;
    /**
     * Mensaje de error generado
     */
    private String message;
    /**
     * Código de estatus http
     */
    private Integer codeHttp;
    /**
     * Url de la petición que generó el error
     */
    @Accessors(chain = true)
    private String url;
    /**
     * Metodo de la peticion que generó error
     */
    @Accessors(chain = true)
    private String metodo;

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCodeHttp(Integer codeHttp) {
        this.codeHttp = codeHttp;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
