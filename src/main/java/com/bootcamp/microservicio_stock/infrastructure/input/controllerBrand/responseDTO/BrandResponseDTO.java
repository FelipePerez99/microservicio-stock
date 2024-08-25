package com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponseDTO {
    private int id;
    private String name;
    private String description;
}
