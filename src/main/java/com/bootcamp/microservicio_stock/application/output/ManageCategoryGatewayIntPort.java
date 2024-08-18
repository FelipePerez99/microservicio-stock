package com.bootcamp.microservicio_stock.application.output;

import com.bootcamp.microservicio_stock.domain.models.Category;

public interface ManageCategoryGatewayIntPort {
    public Category save(Category objCategory);
    public boolean existsCategoryByName(String name);
}
