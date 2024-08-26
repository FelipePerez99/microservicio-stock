package com.bootcamp.microservicio_stock.application.output;

import com.bootcamp.microservicio_stock.domain.models.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManageBrandGatewayIntPort {
    public Brand save(Brand objBrand);
    public boolean existsBrandyByName(String name);
    Page<Brand> findAllBrands(Pageable pageable);
}
