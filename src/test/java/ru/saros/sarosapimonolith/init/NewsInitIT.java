package ru.saros.sarosapimonolith.init;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.saros.sarosapimonolith.api.repositories.NewsRepository;
import ru.saros.sarosapimonolith.api.services.NewsService;
import ru.saros.sarosapimonolith.models.entities.News;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsInitIT {

    private final NewsRepository newsRepository;

    private final NewsService newsService;

    private List<Long> ids = new ArrayList<>();

    News news;

    @PostConstruct
    private void initNews() {
        for (int i = 0; i < 11; i++) {
            news = new News();
            news.setTitle("Title number: " + i);
            news.setDescription("Description number: " + i);
            ids.add(newsService.createNews(news).getId());
        }
    }

    @PreDestroy
    private void deleteCreatedNews() {
        for (Long id : ids) {
            newsRepository.deleteById(id);
        }
    }
}
