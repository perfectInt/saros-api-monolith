package ru.saros.sarosapimonolith.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saros.sarosapimonolith.api.repositories.ImageRepository;
import ru.saros.sarosapimonolith.exceptions.ImageNotFoundException;
import ru.saros.sarosapimonolith.models.entities.Image;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("Cannot find this image"));
    }
}
