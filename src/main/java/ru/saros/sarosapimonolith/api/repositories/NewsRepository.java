package ru.saros.sarosapimonolith.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.saros.sarosapimonolith.models.entities.News;

public interface NewsRepository extends PagingAndSortingRepository<News, Long> {
}

