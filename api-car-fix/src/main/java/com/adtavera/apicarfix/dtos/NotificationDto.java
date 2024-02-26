package com.adtavera.apicarfix.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NotificationDto {

    @NotEmpty(message = "Description required")
    private String description;

    private LocalDate date;

    private Integer km;

}
