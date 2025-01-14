package me.kimdeaung.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kimdeaung.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity(){  // 생성자를 이용 객체 생성

        return Article.builder()
                .title(title)
                .content(content)
                .build();

    }

}
