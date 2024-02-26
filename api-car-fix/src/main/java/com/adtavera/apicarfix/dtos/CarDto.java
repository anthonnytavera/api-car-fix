package com.adtavera.apicarfix.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {
    @NotEmpty(message = "Brand is required")
    private String brand;

    @NotEmpty(message = "Model is required")
    private String model;

    @NotNull(message = "Year is required")
    private Integer year;

    @NotNull(message = "Km is required")
    private Integer km;

    @NotNull(message = "User Id is required")
    private Long userId;
}
