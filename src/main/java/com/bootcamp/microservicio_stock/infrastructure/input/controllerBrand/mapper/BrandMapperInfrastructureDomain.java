package com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.mapper;

import com.bootcamp.microservicio_stock.domain.models.Brand;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.requestDTO.BrandRequestDTO;
import com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.responseDTO.BrandResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapperInfrastructureDomain {
    Brand mapRequestBrand(BrandRequestDTO request);
    BrandResponseDTO mapBrandResponse(Brand objBrand);
}
