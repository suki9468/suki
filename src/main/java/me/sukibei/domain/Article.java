package me.sukibei.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
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
        public Article(String title, String content){
            this.title = title;
            this.content = content;
        }
        public void update(String title,String content){
            this.title = title;
            this.content = content;
        }

    }

