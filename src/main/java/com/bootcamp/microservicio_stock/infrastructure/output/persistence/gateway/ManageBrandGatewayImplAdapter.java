package com.bootcamp.microservicio_stock.infrastructure.output.persistence.gateway;

import com.bootcamp.microservicio_stock.application.output.ManageBrandGatewayIntPort;
import com.bootcamp.microservicio_stock.domain.models.Brand;
import com.bootcamp.microservicio_stock.infrastructure.output.persistence.entity.BrandEntity;
import com.bootcamp.microservicio_stock.infrastructure.output.persistence.repository.BrandRepositoryInt;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManageBrandGatewayImplAdapter implements ManageBrandGatewayIntPort {

    private final BrandRepositoryInt objBrandRepository;
    private final ModelMapper brandModelMapper;

    public ManageBrandGatewayImplAdapter(BrandRepositoryInt objBrandRepository, @Qualifier("brandMapper") ModelMapper brandModelMapper) {
        this.objBrandRepository = objBrandRepository;
        this.brandModelMapper = brandModelMapper;
    }

    @Transactional
    @Override
    public Brand save(Brand objBrand) {
        BrandEntity objBrandEntity = this.brandModelMapper.map(objBrand, BrandEntity.class);
        Brand objBrandResponse = null;
        BrandEntity objBrandEntityRegistered = this.objBrandRepository.save(objBrandEntity);
        objBrandResponse = this.brandModelMapper.map(objBrandEntityRegistered, Brand.class);
        return objBrandResponse;
    }

    @Override
    public boolean existsBrandyByName(String name) {
        return this.objBrandRepository.existsBrandByName(name) == 1;
    }

    @Override
    public Page<Brand> findAllBrands(Pageable pageable) {
        Page<BrandEntity> brandEntities = this.objBrandRepository.findAll(pageable);
        return brandEntities.map(brandEntity -> this.brandModelMapper.map(brandEntity, Brand.class));
    }
}
