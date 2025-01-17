package me.ahngeunsu.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.Article;
import me.ahngeunsu.springbootdeveloper.dto.AddArticleRequest;
import me.ahngeunsu.springbootdeveloper.dto.UpdateArticleRequest;
import me.ahngeunsu.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor        // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service                        // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;


    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }


    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) { // 이 경우 결과값은 하나밖에 없기 때문에 리턴 타입이 Article이어야만 합니다. -> id PK라서
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));   // 람다식
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }


    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));


        article.update(request.getTitle(), request.getContent());

        return article;
    }

}
