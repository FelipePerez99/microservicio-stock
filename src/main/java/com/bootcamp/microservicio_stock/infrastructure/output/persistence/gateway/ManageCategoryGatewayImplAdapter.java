package com.bootcamp.microservicio_stock.infrastructure.output.persistence.gateway;

import com.bootcamp.microservicio_stock.application.output.ManageCategoryGatewayIntPort;
import com.bootcamp.microservicio_stock.domain.models.Category;
import com.bootcamp.microservicio_stock.infrastructure.output.persistence.entity.CategoryEntity;
import com.bootcamp.microservicio_stock.infrastructure.output.persistence.repository.CategoryRepositoryInt;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

@RequiredArgsConstructor
@Service
public class ManageCategoryGatewayImplAdapter implements ManageCategoryGatewayIntPort {

    private final CategoryRepositoryInt objCategoryRepository;
    private final ModelMapper categoryModelMapper;

    @Override
    public Category save(Category objCategory) {
        CategoryEntity objCategoryEntity = this.categoryModelMapper.map(objCategory, CategoryEntity.class);
        Category objCategoryResponse = null;
        CategoryEntity objCategoryEntityRegistered = this.objCategoryRepository.save(objCategoryEntity);
        objCategoryResponse = this.categoryModelMapper.map(objCategoryEntityRegistered, Category.class);
        return objCategoryResponse;
    }

    @Override
    public boolean existsCategoryByName(String name) {
        return false;
    }
}
