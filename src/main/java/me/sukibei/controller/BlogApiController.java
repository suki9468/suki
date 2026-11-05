package me.sukibei.controller;


import lombok.RequiredArgsConstructor;
import me.sukibei.domain.Article;
import me.sukibei.dto.AddArticleRequest;
import me.sukibei.dto.ArticleResponse;
import me.sukibei.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);

    }


    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>>getAllArticles(){
        //List<Article> articleList =  blogService.getAll();
        //List<ArticleResponse> articleResponseList = new ArrayList<>();
      //  for (Article article: articleList){
          //  ArticleResponse ar = new ArticleResponse(article);
           // articleResponseList.add(ar);
        //}
        List<ArticleResponse> articleResponseList = blogService.getAll()
                                            .stream()
                                            //.map(a->new ArticleResponse(a))
                                            .map(ArticleResponse::new)
                                            .toList();
        return ResponseEntity.ok().body(articleResponseList);
    }


   // public BlogApiController(BlogService blogService){
     //   this.blogService = service;
    //}
}
