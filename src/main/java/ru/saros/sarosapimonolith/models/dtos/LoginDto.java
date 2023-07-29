package ru.saros.sarosapimonolith.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
    private String email;
    private String password;
}
