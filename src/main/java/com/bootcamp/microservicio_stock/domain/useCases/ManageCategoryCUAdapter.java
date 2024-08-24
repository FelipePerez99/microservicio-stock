package com.bootcamp.microservicio_stock.domain.useCases;

import com.bootcamp.microservicio_stock.application.input.ManageCategoryCUIntPort;
import com.bootcamp.microservicio_stock.application.output.FormatterResultsIntPort;
import com.bootcamp.microservicio_stock.application.output.ManageCategoryGatewayIntPort;
import com.bootcamp.microservicio_stock.domain.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ManageCategoryCUAdapter implements ManageCategoryCUIntPort {

    private final ManageCategoryGatewayIntPort objManageCategoryGateway;
    private final FormatterResultsIntPort objFormatterResultsIntPort;

    public ManageCategoryCUAdapter(ManageCategoryGatewayIntPort objManageCategoryGateway, FormatterResultsIntPort objFormatterResultsIntPort) {
        this.objManageCategoryGateway = objManageCategoryGateway;
        this.objFormatterResultsIntPort = objFormatterResultsIntPort;
    }

    @Override
    public Category create(Category objCategory) {
        Category objCreatedCategory = null;
        if(this.objManageCategoryGateway.existsCategoryByName(objCategory.getName())) {
            this.objFormatterResultsIntPort.returnResponseErrorEntityExists("Error, en el sistema se encuentra una categoria con ese nombre");
        }else{
            objCreatedCategory = this.objManageCategoryGateway.save(objCategory);
        }
        return objCreatedCategory;
    }

    @Override
    public Page<Category> listCategories(int page, int size, String sortBy, boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return objManageCategoryGateway.findAll(pageable);
    }
}
