package me.kimdeaung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.kimdeaung.springbootdeveloper.domain.Article;
import me.kimdeaung.springbootdeveloper.dto.AddArticleRequest;
import me.kimdeaung.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;
    //HTTP 메서드가 POST일때 전달 받은 url 과 동일하면 지금 정의하는 메서드와 매핑
    @PostMapping("/api/articles")
    // @ResponseBody 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }
    /*
        @RestController : 클래스에 붙이면 HTTP 응답으로 객체 데이터를 "JSON"형식으로 변환
        @PostMapping : HTTP 메서드가 POST 일때 요청받은 URL 과 동일한 메서드와 매핑
            지금의 경우/api/articles 는 addArticle()메서드와 매핑
        @RequestBody : HTTP 요청시 응답에 해당하는 값을 @RequestBody 애너테이션이 붙은
            대상 객체는 AddArticleRequest 에 매핑
        ResponseEntity.status().body() 는 응답 코드로 201, 즉  Created 를 응답하고
            테이블에 저장된 객체 반환

        200 OK : 요청이 성공적으로 수행되었음
        201 Created : 요청이 성공적으로 수행되었고, 새로운 리소스가 생성되었음
        400 Bad Request : 요청 값이 잘못되어 요청에 실패했음
        403 Forbidden : 권한이 없어 요청에 실패했음
        404 Not Found : 요청 값으로 찾은 리소스가 없어 요청에 실패했음
        500 Internal Server Error : 서버 상에 문제가 있어 요청에 실패했음

        API 작동하는지 확인예정

            H2 콘솔 활성화

            application.yml 드가서 수정
            http 매서드 : post
            URL : http://localhost

            결과값이 body 에 pretty 모드로 결과를 보여줬다.
            ->> 여기까지 성공했다면 스프링부트 서버에 저장된것을 의미한다

            여기까지의 과정이 HTTP 메서드 POST 로 서버에 요청을 한 후 값을 저장하는 과정에 해당

            크롬

            Sql statement 입력창에
                select * from article
     */
}
