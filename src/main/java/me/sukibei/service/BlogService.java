package me.sukibei.service;


import lombok.RequiredArgsConstructor;
import me.sukibei.domain.Article;
import me.sukibei.dto.AddArticleRequest;
import me.sukibei.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {
    //@Autowired
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest dto){
        return blogRepository.save(dto. toEntity());
    }
}
