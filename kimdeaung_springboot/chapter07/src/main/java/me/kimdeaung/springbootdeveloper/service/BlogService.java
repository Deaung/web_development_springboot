package me.kimdeaung.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.kimdeaung.springbootdeveloper.domain.Article;
import me.kimdeaung.springbootdeveloper.dto.AddArticleRequest;
import me.kimdeaung.springbootdeveloper.dto.UpdateArticleRequest;
import me.kimdeaung.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final 이 붙거나 @NotNull이 붙은 필드의 생성지 추가
@Service                 // 빈으로 등록

public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }
    /*
        @Service : 해당 클래스를 빈으로 서블릿 컨테이너에 등록
        save() : JpaRepository 에서 지원하는 저장 메서드 save()로
            AddArticleRequest 클래스에 저장된 값들을 article 데이터베이스에 저장합니다.

        ->> 글을 생성하는 서비스 계층에서의 ㅇ코드작성 완료

        >>> 컨트롤러 메서드 코드 작성하기
            url 에 매핑하기 위한 컨트롤러 메서드 추가
            컨트롤러 메서드에는 url 매핑 에너테이션인
            @PostMapping
            @GetMapping
            @PutMapping
            @DeleteMapping
            이름에서 유추 할 수잇듯 각 에너테이션은 HTTP 매서드와 대응

            /api/articles 에 POST 요청이 들어오면 @PostMapping 을 이용해 요청을 매핑한 뒤
            블로그 글을 생성하는 BlogService 의 save() 매서드를 호출
            >> 생성된 블로그 글을 반환하는 작업을 하는 addArticle() 매서드를 작성 예정

            지시사앟ㅇ
            service 와 동일한 라인에 controller 패키지 생성 후 BlogApiController.java생성
     */

    public List<Article> findAll(){
        return blogRepository.findAll();
    }
    /*
        JPA 지원 메서드인 findAll()을 호출해 article 테이블에 저장되어있는 모든 데이터를 조회,

        응답을 위한 DTO 생성
        dto 패키지에 ArticleResponse.java
     */

    // id 로 특정 글 조회

    public Article findById(long id){                   // 이 경우 결과값이 하나뿐이기에 리턴 값 Article -- id는 PK 값이기에
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id)); // 람다식
    }

    // 삭제 메서드 정리
    public void delete(long id){
        blogRepository.deleteById(id);
    }
    // 컨트롤러  /api/articles/{id}

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        // domain -> Article 에서 정의한 update()를 사용.
        article.update(request.getTitle(), request.getContent());

        return article;
    }
    /*
        트랜젝션 - 데이터베이스에서 데이터를 바꾸기 위한 작업 단위

            예를 들어 계좌 이체를 할 때 이런 과정을 거친다고 가정
                1) a 계좌에서 출금
                2) b 계좌에 입금\

            1)은 성공했으나 도중에 2)가 실패하면 어떻게되나 출금은 됬으나 입금이 안되는 상황 발생
            이런 상황 발생 안하도록 출.입금을 하나의 작업 단위로 묶어 , 즉 트랜젝션이라는 단위로 두 ㅠ작업을
            한 단위로 실행하게 됨 실패시 다시 트랜젝션 실행/롤백
     */

}
