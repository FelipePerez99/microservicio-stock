package com.bootcamp.microservicio_stock.infrastructure.output.persistence.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrandMapper {
    @Bean
    @Qualifier("brandMapper")
    public ModelMapper mapperBrand() {
        return new ModelMapper();
    }
}
