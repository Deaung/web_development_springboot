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
    /*
        toEntity() 는 빌더 패턴을 사용해 DTO 를  엔티티로 만들어 주는 매서드
        이 메서드는 추후 블로그에 글을 추가 할 때 저장할 엔티티로 변환하는 용도로 사용

        dto 와 동일한 라인에 service 패키지 생성 -> BlogService.java 클래스 생성
     */
}
