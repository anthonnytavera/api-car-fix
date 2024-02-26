package com.adtavera.apicarfix.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FixDto {

    @NotEmpty(message = "Description required")
    private String description;

    @NotNull(message = "Date is required")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "Km is required")
    private Integer km;

    @NotNull(message = "Price is required")
    private Float price;

    @NotNull(message = "User Id is required")
    private Long carId;
}
