package com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.RequestDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {
    private int id;

    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 3 , max = 50)
    private String name;

    @Size(min = 5 , max = 90)
    private String description;
}
