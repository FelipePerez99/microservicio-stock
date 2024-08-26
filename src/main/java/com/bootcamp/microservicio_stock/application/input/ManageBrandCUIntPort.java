package com.bootcamp.microservicio_stock.application.input;

import com.bootcamp.microservicio_stock.domain.models.Brand;
import org.springframework.data.domain.Page;

public interface ManageBrandCUIntPort {
    public Brand createBrand(Brand objBrand);
    Page<Brand> listBrands(int page, int size, String sortBy, boolean ascending);
}
