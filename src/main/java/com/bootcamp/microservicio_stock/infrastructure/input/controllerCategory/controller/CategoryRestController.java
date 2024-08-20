package com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.controller;

import com.bootcamp.microservicio_stock.application.input.ManageCategoryCUIntPort;
import com.bootcamp.microservicio_stock.domain.models.Category;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.RequestDTO.CategoryRequestDTO;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.ResponseDTO.CategoryResponseDTO;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.mapper.CategoryMapperInfrastructureDomain;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class CategoryRestController {

    private final ManageCategoryCUIntPort objManageCategoryCUInt;
    private final CategoryMapperInfrastructureDomain objCategoryMapper;

    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryRequestDTO objCategory){
        Category objCategoryCreate = objCategoryMapper.mapRequestCategory(objCategory);
        Category objCategoryCreated = objManageCategoryCUInt.create(objCategoryCreate);
        ResponseEntity<CategoryResponseDTO> objResponse = new ResponseEntity<CategoryResponseDTO>(
                objCategoryMapper.mapCategoryResponse(objCategoryCreated), HttpStatus.CREATED);
        return objResponse;

    }

}
