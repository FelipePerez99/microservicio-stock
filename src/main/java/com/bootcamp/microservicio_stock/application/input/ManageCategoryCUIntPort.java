package com.bootcamp.microservicio_stock.application.input;

import com.bootcamp.microservicio_stock.domain.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ManageCategoryCUIntPort {
    public Category create(Category objCategory);
    Page<Category> listCategories(int page, int size, String sortBy, boolean ascending);
}
