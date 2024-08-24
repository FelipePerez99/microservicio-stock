package com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.controller;

import com.bootcamp.microservicio_stock.application.input.ManageCategoryCUIntPort;
import com.bootcamp.microservicio_stock.domain.models.Category;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.RequestDTO.CategoryRequestDTO;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.ResponseDTO.CategoryResponseDTO;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.mapper.CategoryMapperInfrastructureDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
@Validated
public class CategoryRestController {

    private final ManageCategoryCUIntPort objManageCategoryCUInt;
    private final CategoryMapperInfrastructureDomain objCategoryMapper;

    public CategoryRestController(ManageCategoryCUIntPort objManageCategoryCUInt, CategoryMapperInfrastructureDomain objCategoryMapper) {
        this.objManageCategoryCUInt = objManageCategoryCUInt;
        this.objCategoryMapper = objCategoryMapper;
    }

    @Operation(summary = "Create a new category", description = "Allows the administrator to create a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully" ,
                content = @Content(schema = @Schema(implementation = CategoryResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })

    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryRequestDTO objCategory){
        Category objCategoryCreate = objCategoryMapper.mapRequestCategory(objCategory);
        Category objCategoryCreated = objManageCategoryCUInt.create(objCategoryCreate);
        ResponseEntity<CategoryResponseDTO> objResponse = new ResponseEntity<CategoryResponseDTO>(
                objCategoryMapper.mapCategoryResponse(objCategoryCreated), HttpStatus.CREATED);
        return objResponse;

    }

    @Operation(summary = "List categories with pagination and sorting",
            description = "Retrieve a paginated list of categories, sorted by the specified field.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CategoryResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponseDTO>> listCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {

        Page<Category> categoryPage = objManageCategoryCUInt.listCategories(page, size, sortBy, ascending);

        List<CategoryResponseDTO> categoryResponseList = categoryPage.stream()
                .map(objCategoryMapper::mapCategoryResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(categoryResponseList, HttpStatus.OK);
    }

}
