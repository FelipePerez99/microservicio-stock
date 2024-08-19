package com.bootcamp.microservicio_stock.infrastructure.output.formatter;

import com.bootcamp.microservicio_stock.application.output.FormatterResultsIntPort;
import com.bootcamp.microservicio_stock.infrastructure.output.controllerExceptions.Exceptions.EntityAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class FormatterResultsImplAdapter implements FormatterResultsIntPort {
    @Override
    public void returnResponseErrorEntityExists(String message) {
        EntityAlreadyExistsException objException = new EntityAlreadyExistsException(message);
        throw objException;
    }
}
