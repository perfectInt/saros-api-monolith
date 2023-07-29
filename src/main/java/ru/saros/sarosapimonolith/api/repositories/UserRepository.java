package ru.saros.sarosapimonolith.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.saros.sarosapimonolith.models.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
