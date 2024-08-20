package com.bootcamp.microservicio_stock.infrastructure.output.persistence.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryMapper {
    @Bean
    public CategoryMapper categoryMapper() {
        return new CategoryMapper();
    }
}
