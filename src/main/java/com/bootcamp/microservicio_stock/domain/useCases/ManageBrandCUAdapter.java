package com.bootcamp.microservicio_stock.domain.useCases;


import com.bootcamp.microservicio_stock.application.input.ManageBrandCUIntPort;
import com.bootcamp.microservicio_stock.application.output.FormatterResultsIntPort;
import com.bootcamp.microservicio_stock.application.output.ManageBrandGatewayIntPort;
import com.bootcamp.microservicio_stock.domain.models.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Override
    public Page<Brand> listBrands(int page, int size, String sortBy, boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return objManageBrandGateway.findAllBrands(pageable);
    }
}
