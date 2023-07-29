package ru.saros.sarosapimonolith.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.saros.sarosapimonolith.models.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
