package com.bootcamp.microservicio_stock.infrastructure.input.controllerCategory.RequestDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryRequestDTO {
    private int id;

    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 3 , max = 50)
    private String name;

    @NotNull(message = "La descripción no puede estar vacía")
    @Size(min = 5 , max = 90)
    private String description;

    public CategoryRequestDTO() {
    }

    public CategoryRequestDTO(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "El nombre no puede estar vacio") @Size(min = 3, max = 50) String getName() {
        return name;
    }

    public void setName(@NotNull(message = "El nombre no puede estar vacio") @Size(min = 3, max = 50) String name) {
        this.name = name;
    }

    public @NotNull(message = "La descripción no puede estar vacía") @Size(min = 5, max = 90) String getDescription() {
        return description;
    }

    public void setDescription(@NotNull(message = "La descripción no puede estar vacía") @Size(min = 5, max = 90) String description) {
        this.description = description;
    }
}
