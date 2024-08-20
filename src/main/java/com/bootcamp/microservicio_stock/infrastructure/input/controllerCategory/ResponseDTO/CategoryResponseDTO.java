package com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private int id;
    private String name;
    private String description;
}
