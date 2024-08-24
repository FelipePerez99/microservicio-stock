package com.bootcamp.microservicio_stock.infrastructure.output.persistence.repository;


import com.bootcamp.microservicio_stock.infrastructure.output.persistence.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrandRepositoryInt extends JpaRepository<BrandEntity, Integer> {
    @Query("SELECT count(*) FROM BrandEntity b  WHERE b.name=?1")
    Integer existsBrandByName(String name);
}
