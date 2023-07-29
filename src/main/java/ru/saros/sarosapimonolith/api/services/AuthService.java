package ru.saros.sarosapimonolith.api.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.saros.sarosapimonolith.exceptions.PasswordException;
import ru.saros.sarosapimonolith.exceptions.UserAlreadyExistsException;
import ru.saros.sarosapimonolith.models.dtos.LoginDto;
import ru.saros.sarosapimonolith.models.dtos.RegisterDto;
import ru.saros.sarosapimonolith.models.entities.User;
import ru.saros.sarosapimonolith.security.models.JwtAuthentication;
import ru.saros.sarosapimonolith.security.utils.JwtProvider;

import javax.security.auth.message.AuthException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    public String login(@NonNull LoginDto authRequest) throws AuthException {
        final User user = userService.findByEmail(authRequest.getEmail());
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            return jwtProvider.generateAccessToken(user);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong password");
        }
    }

    public String register(@NonNull RegisterDto request) throws AuthException {
        if (!userService.existsByEmail(request.getEmail())) {
            if (Objects.equals(request.getPassword(), request.getPasswordConfirmation())) {
                userService.createUser(request);
            } else {
                throw new PasswordException();
            }
        } else {
            throw new UserAlreadyExistsException();
        }
        return login(new LoginDto(request.getEmail(), request.getPassword()));
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
