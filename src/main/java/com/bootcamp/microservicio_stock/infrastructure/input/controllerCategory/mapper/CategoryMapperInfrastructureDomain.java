package com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.mapper;

import com.bootcamp.microservicio_stock.domain.models.Category;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.RequestDTO.CategoryRequestDTO;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.ResponseDTO.CategoryResponseDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapperInfrastructureDomain {
    Category mapRequestCategory(CategoryRequestDTO request);
    CategoryResponseDTO mapCategoryResponse(Category objCategory);

}
