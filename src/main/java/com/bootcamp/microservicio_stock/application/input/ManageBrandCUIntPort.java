package com.bootcamp.microservicio_stock.application.input;

import com.bootcamp.microservicio_stock.domain.models.Brand;

public interface ManageBrandCUIntPort {
    public Brand createBrand(Brand objBrand);
}
