package com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.controller;

import com.bootcamp.microservicio_stock.application.input.ManageBrandCUIntPort;
import com.bootcamp.microservicio_stock.domain.models.Brand;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.mapper.BrandMapperInfrastructureDomain;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.requestDTO.BrandRequestDTO;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.responseDTO.BrandResponseDTO;
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

    @PostMapping("/brand")
    public ResponseEntity<BrandResponseDTO> create(@Valid @RequestBody BrandRequestDTO objBrand){
        Brand objBrandCreate = objBrandMapper.mapRequestBrand(objBrand);
        Brand objBrandCreated = objManageBrandCUInt.createBrand(objBrandCreate);
        ResponseEntity<BrandResponseDTO> objResponse = new ResponseEntity<BrandResponseDTO>(
                objBrandMapper.mapBrandResponse(objBrandCreated), HttpStatus.CREATED);
        return objResponse;
    }
}
