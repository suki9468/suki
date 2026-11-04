package me.sukibei.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.sukibei.domain.Article;
import me.sukibei.dto.AddArticleRequest;
import me.sukibei.repository.BlogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogRepository blogRepository;

    @DisplayName("블로그 글 추가에 심공한다.")
    @Test
    public void addArticle() throws Exception{


        String title = "111";
        String content = "aaaaaaaa ";
        AddArticleRequest req = new AddArticleRequest(title,content);
        String reqBody = objectMapper.writeValueAsString(req);
        String url = "/api/articles";

        ResultActions result =  mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(reqBody));

        result.andExpect(status().isCreated());
       List<Article> articles = blogRepository.findAll();
       assertThat(articles.size()).isEqualTo(1);
       assertThat(articles.get(0).getTitle()).isEqualTo(title);
       assertThat(articles.get(0).getContent()).isEqualTo(content);



    }

}