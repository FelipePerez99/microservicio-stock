package com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.mapper;

import com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.RequestDTO.CategoryRequestDTO;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.ResponseDTO.CategoryResponseDTO;
import com.bootcamp.microservicio_stock.infrastructure.output.persistence.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapperInfrastructureDomain {
    Category mapRequestCategory(CategoryRequestDTO request);
    CategoryResponseDTO mapCategoryResponse(Category objCategory);

}
