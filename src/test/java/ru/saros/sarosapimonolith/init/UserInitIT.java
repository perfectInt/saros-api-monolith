package ru.saros.sarosapimonolith.init;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.saros.sarosapimonolith.api.repositories.UserRepository;
import ru.saros.sarosapimonolith.api.services.UserService;
import ru.saros.sarosapimonolith.models.entities.User;
import ru.saros.sarosapimonolith.security.models.Role;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@RequiredArgsConstructor
public class UserInitIT {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserService userService;

    Long id;

    @PostConstruct
    public void initUser() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setUserRole(Role.ADMIN.getAuthority());
        user.setPassword(passwordEncoder.encode("lalala"));
        user.setFirstName("Rusya");
        user.setLastName("Perfect");
        id = userRepository.save(user).getId();
    }

    @PreDestroy
    public void deleteCreatedUser() {
        userRepository.deleteById(id);
    }
}
