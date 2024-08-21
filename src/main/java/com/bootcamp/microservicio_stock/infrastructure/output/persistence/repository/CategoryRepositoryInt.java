package com.bootcamp.microservicio_stock.infrastructure.output.persistence.repository;

import com.bootcamp.microservicio_stock.infrastructure.output.persistence.entity.CategoryEntity;
import jakarta.persistence.Cache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoryInt extends JpaRepository<CategoryEntity, Integer>{
}
