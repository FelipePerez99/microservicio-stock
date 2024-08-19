package com.bootcamp.microservicio_stock.infrastructure.configuration;


import com.bootcamp.microservicio_stock.application.output.FormatterResultsIntPort;
import com.bootcamp.microservicio_stock.application.output.ManageCategoryGatewayIntPort;
import com.bootcamp.microservicio_stock.domain.useCases.ManageCategoryCUAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurations {
    @Bean
    public ManageCategoryCUAdapter createManageCategoryCUInt(ManageCategoryGatewayIntPort objManageCategoryGateway, FormatterResultsIntPort objFormatterResults) {
        ManageCategoryCUAdapter objManageCategoryCU = new ManageCategoryCUAdapter(objManageCategoryGateway, objFormatterResults);
        return objManageCategoryCU;
    }
}
