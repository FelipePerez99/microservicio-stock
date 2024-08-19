package com.bootcamp.microservicio_stock.infrastructure.output.persistence.gateway;

import com.bootcamp.microservicio_stock.application.output.ManageCategoryGatewayIntPort;
import com.bootcamp.microservicio_stock.domain.models.Category;
import org.springframework.stereotype.Service;

@Service
public class ManageCategoryGatewayImplAdapter implements ManageCategoryGatewayIntPort {
    @Override
    public Category save(Category objCategory) {
        return null;
    }

    @Override
    public boolean existsCategoryByName(String name) {
        return false;
    }
}
