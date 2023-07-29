package ru.saros.sarosapimonolith.mappers;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.saros.sarosapimonolith.models.entities.Image;

import java.io.IOException;

@Component
public class ImageMapper {

    public Image toEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
