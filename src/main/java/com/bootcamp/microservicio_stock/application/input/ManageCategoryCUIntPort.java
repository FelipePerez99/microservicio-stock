package com.bootcamp.microservicio_stock.application.input;

import com.bootcamp.microservicio_stock.domain.models.Category;
import org.springframework.data.domain.Page;
import java.awt.print.Pageable;

public interface ManageCategoryCUIntPort {
    public Category create(Category objCategory);
    public Page<Category> listCategories(Pageable pageable);
}
