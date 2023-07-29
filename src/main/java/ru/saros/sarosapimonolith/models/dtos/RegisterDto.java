package ru.saros.sarosapimonolith.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String email;
    private String password;
    private String passwordConfirmation;
    private String firstName;
    private String lastName;
}
