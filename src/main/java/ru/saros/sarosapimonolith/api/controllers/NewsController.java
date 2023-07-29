package ru.saros.sarosapimonolith.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.saros.sarosapimonolith.models.entities.News;
import ru.saros.sarosapimonolith.models.views.NewsView;
import ru.saros.sarosapimonolith.api.services.NewsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news/{id}")
    public NewsView getNewsById(@PathVariable Long id) {
        return newsService.getNewsById(id);
    }

    @GetMapping("/news")
    public List<NewsView> getNewsByPage(@RequestParam(name = "page", required = false) Integer page) {
        return newsService.getNewsByPages(page);
    }

    @PostMapping("/news/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public News createNews(@RequestBody News news) {
        return newsService.createNews(news);
    }

    @PutMapping("/news/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateNews(@RequestBody News news) {
        newsService.updateNews(news);
    }

    @DeleteMapping("/news/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
    }
}