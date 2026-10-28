package me.sukibei.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sukibei.domain.Article;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;

    //public AddArticleRequest(){
       // super();
    //}

    public Article toEntity(){
        return new Article(title, content);
        //return Article.builder().title(title).content(content).build();
    }

}
