package me.sukibei.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)


        private long id;
        private String title;
        private String content;


        @Builder
        public Article(String tiltle, String content){
            this.title = title;
            this.content = content;
        }
    }

