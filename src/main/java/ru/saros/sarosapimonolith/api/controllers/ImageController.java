package ru.saros.sarosapimonolith.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.saros.sarosapimonolith.models.entities.Image;
import ru.saros.sarosapimonolith.api.services.ImageService;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/image/{id}")
    public ResponseEntity<?> getImageById(@PathVariable Long id) {
        Image image = imageService.getImageById(id);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
