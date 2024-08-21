package com.bootcamp.microservicio_stock.infrastructure.output.persistence.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryMapper {
    @Bean
    public ModelMapper categoryMapper() {
        return new ModelMapper();
    }
}
