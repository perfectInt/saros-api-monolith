package ru.saros.sarosapimonolith.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.saros.sarosapimonolith.models.entities.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query("UPDATE Product p set p.previewImageId = :previewImageId where p.id = :id")
    @Modifying
    void updateByProductIdItsPreviewImageId(Long id, Long previewImageId);

    Page<Product> findAllByCategory(Pageable pageable, String category);
}
