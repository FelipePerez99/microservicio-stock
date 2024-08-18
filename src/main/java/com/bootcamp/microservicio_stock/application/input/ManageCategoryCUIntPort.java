package com.bootcamp.microservicio_stock.application.input;

import com.bootcamp.microservicio_stock.domain.models.Category;

public interface ManageCategoryCUIntPort {
    public Category create(Category objCategory);
}
