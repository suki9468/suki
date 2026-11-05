package me.sukibei.dto;


import lombok.Getter;
import me.sukibei.domain.Article;

@Getter
public class ArticleResponse {
    private String title;
    private String content;


    public ArticleResponse(Article entity){
        this.title = entity.getTitle();
        this.content = entity.getContent();

    }

}
