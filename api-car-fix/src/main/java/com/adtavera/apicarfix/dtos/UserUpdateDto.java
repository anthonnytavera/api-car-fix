package com.adtavera.apicarfix.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {

    @NotEmpty(message = "First Name required")
    private String firstName;

    @NotEmpty(message = "Last Name required")
    private String lastName;

    @Email(message = "Email invalid")
    @NotEmpty(message = "Email required")
    private String email;

}
