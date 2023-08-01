package ru.saros.sarosapimonolith.api.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.saros.sarosapimonolith.api.repositories.UserRepository;
import ru.saros.sarosapimonolith.exceptions.UserNotFoundException;
import ru.saros.sarosapimonolith.models.dtos.RegisterDto;
import ru.saros.sarosapimonolith.models.entities.User;
import ru.saros.sarosapimonolith.security.models.Role;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public Long createUser(RegisterDto request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserRole(Role.USER.getAuthority());
        if (getAllUsers().isEmpty()) {
            user.setUserRole(Role.ADMIN.getAuthority());
        }
        return userRepository.save(user).getId();
    }
}
