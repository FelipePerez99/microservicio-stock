package com.bootcamp.microservicio_stock.infrastructure.input.controllerBrand.requestDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandRequestDTO {
    private int id;

    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 3 , max = 50)
    private String name;

    @NotNull(message = "La descripción no puede estar vacía")
    @Size(min = 5 , max = 120)
    private String description;
}
