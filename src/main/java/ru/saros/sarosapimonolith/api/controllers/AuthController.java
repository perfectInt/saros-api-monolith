package ru.saros.sarosapimonolith.api.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.saros.sarosapimonolith.api.services.AuthService;
import ru.saros.sarosapimonolith.models.dtos.LoginDto;
import ru.saros.sarosapimonolith.models.dtos.RegisterDto;

import javax.security.auth.message.AuthException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginRequest) throws AuthException {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterDto request) throws AuthException {
        return authService.register(request);
    }
}
