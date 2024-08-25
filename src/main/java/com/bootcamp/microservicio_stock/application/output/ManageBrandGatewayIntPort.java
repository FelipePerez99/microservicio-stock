package com.bootcamp.microservicio_stock.application.output;

import com.bootcamp.microservicio_stock.domain.models.Brand;

public interface ManageBrandGatewayIntPort {
    public Brand save(Brand objBrand);
    public boolean existsBrandyByName(String name);
}
