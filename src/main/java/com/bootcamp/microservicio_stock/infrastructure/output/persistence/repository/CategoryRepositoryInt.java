package com.bootcamp.microservicio_stock.infrastructure.output.persistence.repository;

import jakarta.persistence.Cache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoryInt extends JpaRepository<CategoryRepositoryInt, Integer>{
}
