package ru.saros.sarosapimonolith.mappers;

import org.springframework.stereotype.Component;
import ru.saros.sarosapimonolith.models.entities.Image;
import ru.saros.sarosapimonolith.models.entities.Product;
import ru.saros.sarosapimonolith.models.views.ProductView;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public ProductView toView(Product product) {
        ProductView productView = new ProductView();
        productView.setId(product.getId());
        productView.setTitle(product.getTitle());
        productView.setCategory(product.getCategory());
        productView.setDescription(product.getDescription());
        productView.setPreviewImageId(product.getPreviewImageId());
        productView.setPrice(product.getPrice());
        List<Long> ids = new ArrayList<>();
        for (Image image : product.getImages()) {
            ids.add(image.getId());
        }
        productView.setImagesIds(ids);
        return productView;
    }

    public Product toEntity(String title, String category) {
        Product product = new Product();
        product.setTitle(title);
        product.setCategory(category);
        return product;
    }
}
