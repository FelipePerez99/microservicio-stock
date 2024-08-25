package com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.controller;

import com.bootcamp.microservicio_stock.application.input.ManageBrandCUIntPort;
import com.bootcamp.microservicio_stock.domain.models.Brand;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.mapper.BrandMapperInfrastructureDomain;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.requestDTO.BrandRequestDTO;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.responseDTO.BrandResponseDTO;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.ResponseDTO.CategoryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class BrandRestController {
    private final ManageBrandCUIntPort objManageBrandCUInt;
    private final BrandMapperInfrastructureDomain objBrandMapper;

    public BrandRestController(ManageBrandCUIntPort objManageBrandCUInt, BrandMapperInfrastructureDomain objBrandMapper) {
        this.objManageBrandCUInt = objManageBrandCUInt;
        this.objBrandMapper = objBrandMapper;
    }

    @Operation(summary = "Create a new brand", description = "Allows the administrator to create a new brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully" ,
                    content = @Content(schema = @Schema(implementation = BrandResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })

    @PostMapping("/brand")
    public ResponseEntity<BrandResponseDTO> create(@Valid @RequestBody BrandRequestDTO objBrand){
        Brand objBrandCreate = objBrandMapper.mapRequestBrand(objBrand);
        Brand objBrandCreated = objManageBrandCUInt.createBrand(objBrandCreate);
        ResponseEntity<BrandResponseDTO> objResponse = new ResponseEntity<BrandResponseDTO>(
                objBrandMapper.mapBrandResponse(objBrandCreated), HttpStatus.CREATED);
        return objResponse;
    }
}
