package com.bootcamp.microservicio_stock.infrastructure.output.persistence.gateway;

import com.bootcamp.microservicio_stock.application.output.ManageCategoryGatewayIntPort;
import com.bootcamp.microservicio_stock.domain.models.Category;
import com.bootcamp.microservicio_stock.infrastructure.output.persistence.entity.CategoryEntity;
import com.bootcamp.microservicio_stock.infrastructure.output.persistence.repository.CategoryRepositoryInt;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManageCategoryGatewayImplAdapter implements ManageCategoryGatewayIntPort {

    private final CategoryRepositoryInt objCategoryRepository;
    private final ModelMapper categoryModelMapper;

    public ManageCategoryGatewayImplAdapter(CategoryRepositoryInt objCategoryRepository, @Qualifier("categoryMapper") ModelMapper categoryModelMapper) {
        this.objCategoryRepository = objCategoryRepository;
        this.categoryModelMapper = categoryModelMapper;
    }

    @Transactional
    @Override
    public Category save(Category objCategory) {
        CategoryEntity objCategoryEntity = this.categoryModelMapper.map(objCategory, CategoryEntity.class);
        Category objCategoryResponse = null;
        CategoryEntity objCategoryEntityRegistered = this.objCategoryRepository.save(objCategoryEntity);
        objCategoryResponse = this.categoryModelMapper.map(objCategoryEntityRegistered, Category.class);
        return objCategoryResponse;
    }

    @Override
    public boolean existsCategoryByName(String name){
        return this.objCategoryRepository.existsCategoryByName(name) == 1;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        Page<CategoryEntity> categoryEntities = this.objCategoryRepository.findAll(pageable);
        return categoryEntities.map(categoryEntity -> this.categoryModelMapper.map(categoryEntity, Category.class));
    }
}
