package com.bootcamp.microservicio_stock.infrastructure.output.persistence.repository;

import com.bootcamp.microservicio_stock.infrastructure.output.persistence.entity.CategoryEntity;
import jakarta.persistence.Cache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepositoryInt extends JpaRepository<CategoryEntity, Integer>{
    @Query("SELECT count(*) FROM CategoryEntity c  WHERE c.name=?1")
    Integer existsCategoryByName(String name);
}
