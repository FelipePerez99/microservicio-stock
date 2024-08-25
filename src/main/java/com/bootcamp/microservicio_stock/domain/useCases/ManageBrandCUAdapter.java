package com.bootcamp.microservicio_stock.domain.useCases;


import com.bootcamp.microservicio_stock.application.input.ManageBrandCUIntPort;
import com.bootcamp.microservicio_stock.application.output.FormatterResultsIntPort;
import com.bootcamp.microservicio_stock.application.output.ManageBrandGatewayIntPort;
import com.bootcamp.microservicio_stock.domain.models.Brand;

public class ManageBrandCUAdapter implements ManageBrandCUIntPort {

    private final ManageBrandGatewayIntPort objManageBrandGateway;
    private final FormatterResultsIntPort objFormatterResultsIntPort;

    public ManageBrandCUAdapter(ManageBrandGatewayIntPort objManageBrandGateway, FormatterResultsIntPort objFormatterResultsIntPort) {
        this.objManageBrandGateway = objManageBrandGateway;
        this.objFormatterResultsIntPort = objFormatterResultsIntPort;
    }

    @Override
    public Brand createBrand(Brand objBrand) {
        Brand objCreatedBrand = null;
        if(this.objManageBrandGateway.existsBrandyByName(objBrand.getName())) {
            this.objFormatterResultsIntPort.returnResponseErrorEntityExists("Error, en el sistema se encuentra una marca con ese nombre");
        }else{
            objCreatedBrand = this.objManageBrandGateway.save(objBrand);
        }
        return objCreatedBrand;
    }
}
