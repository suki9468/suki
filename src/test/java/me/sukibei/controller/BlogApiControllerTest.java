package me.sukibei.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.sukibei.domain.Article;
import me.sukibei.dto.AddArticleRequest;
import me.sukibei.dto.UpdateArticleRequest;
import me.sukibei.repository.BlogRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    void cleanUp() {
        blogRepository.deleteAll();
    }

    private Article saveArticle(String title, String content) {
        return blogRepository.save(new Article(title, content));
    }

    @DisplayName("POST /api/articles : 成功添加文字")
    @Test
    void addArticle() throws Exception {
        String title = "11/4周三";
        String content = "进行将数据储存到DB";
        AddArticleRequest request = new AddArticleRequest(title, content);
        String requestBody = objectMapper.writeValueAsString(request);
        String url = "/api/articles";

        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));

        List<Article> articles = blogRepository.findAll();
        assertThat(articles).hasSize(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("GET /api/articles : 成功查询文章")
    @Test
    void getAllArticles() throws Exception {
        saveArticle("1", "1");
        saveArticle("2", "2");

        ResultActions result = mockMvc.perform(get("/api/articles"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("1"))
                .andExpect(jsonPath("$[0].content").value("1"))
                .andExpect(jsonPath("$[1].title").value("2"))
                .andExpect(jsonPath("$[1].content").value("2"));
    }

    @DisplayName("GET /api/articles/{id} : 成功查询文章")
    @Test
    void getArticle() throws Exception {
        Article article = saveArticle("查询标题", "查询内容");
        Long id = article.getId();

        ResultActions result = mockMvc.perform(get("/api/articles/{id}", id));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("查询标题"))
                .andExpect(jsonPath("$.content").value("查询内容"));
    }

    @DisplayName("DELETE /api/articles/{id} : 成功删除文章")
    @Test
    void deleteArticle() throws Exception {
        Article article = saveArticle("被删除的标题", "被删除的内容");
        Long id = article.getId();
        assertThat(blogRepository.existsById(id)).isTrue();

        ResultActions result = mockMvc.perform(delete("/api/articles/{id}", id));

        result.andExpect(status().isOk());
        assertThat(blogRepository.existsById(id)).isFalse();
        assertThat(blogRepository.findAll()).isEmpty();
    }

    @DisplayName("PUT /api/articles/{id} : 成功修改文章")
    @Test
    void updateArticle() throws Exception {
        Article article = saveArticle("修改前标题", "修改前内容");
        Long id = article.getId();
        String newTitle = "修改后标题";
        String newContent = "修改后内容";
        UpdateArticleRequest request = new UpdateArticleRequest(newTitle, newContent);
        String requestBody = objectMapper.writeValueAsString(request);

        ResultActions result = mockMvc.perform(put("/api/articles/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(newTitle))
                .andExpect(jsonPath("$.content").value(newContent));

        Article updated = blogRepository.findById(id).orElseThrow();
        assertThat(updated.getTitle()).isEqualTo(newTitle);
        assertThat(updated.getContent()).isEqualTo(newContent);
    }
}
