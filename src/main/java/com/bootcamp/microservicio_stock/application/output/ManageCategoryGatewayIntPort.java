package com.bootcamp.microservicio_stock.application.output;

import com.bootcamp.microservicio_stock.domain.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ManageCategoryGatewayIntPort {
    public Category save(Category objCategory);
    public boolean existsCategoryByName(String name);
    Page<Category> findAll(Pageable pageable);
}
