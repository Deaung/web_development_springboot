package me.kimdeaung.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kimdeaung.springbootdeveloper.domain.Article;
import me.kimdeaung.springbootdeveloper.dto.AddArticleRequest;
import me.kimdeaung.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest  // 테스트용 어플리케이션 컨텍스트
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; // 직렬화, 역직렬화를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void MockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                        .build();

        blogRepository.deleteAll();
    }
    /*
        처음보는 ObjectMapper 클래스
         이 클래스 만든 자바 객체를 JSON 데이터로 변환하는것 = 직렬화
         역으로 JSON 데이터를 자바 객체로 변환하는것 = 역직렬화

         직렬화 = 자바객체 =json
         역직렬화 - json -> 자바객체

         HTTP 에서는 JSON 을 자바에서는 객체를 사용하는데 서로 형식이 다르기 때문에
         형식에 맞게 변환하는 과정이 필요. 이작업을 직렬화, 역직렬화 라고 함

            1)직렬화 Java 시스탬 내부에서 사용되는 객체를 외부에서 사용하도록
                변환하는 과정을 뜻하는데
                예를들어 title 은 "제목, content 는 "내용" 이라는 값이 들어가 있는 객체 있다 가정했을경우
                자바상에서는

                @AllArgsConstructor
                public class Article{
                    private String title;
                    private String content;

                    main 메서드{
                        Article article = new Article("제목","내용")
                        }
                    }
                    형태로 정리
                    JSON 데이터 상으로는
                    {
                        "title": "제목";
                        "content": "내용";
                    }

            글 생성 API 를 테스트 하는 코드 작성

            given - 블로그 글 추가에 필요한 요청 객체 생성

            when - 블로그 글 추가 API 에 요청을 보냄, 요청 타입은 JSON 이며 given 절에서
                미리 만들어 둔 객체를 요청 본문으로 함께 보냄

            then - 응답 코드가 201 Created 인지 확인
                Blog 를 조회했을 때 전체 크기가 1인지 확인
                실제 저장된 데이터와 요청값을 비교







     */

    @DisplayName("addArticle : 블로그에 글 추가 성공")
    @Test
    public void addArticle() throws Exception{
        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());  // 201 Created 로 나오는지 확인

        List<Article> articles = blogRepository.findAll();  //repository 에서 전체 글을 가져와 리스트에 담았음

        assertThat(articles.size()).isEqualTo(1); // 리스트 내의 element 개수가 1이면 true

        assertThat(articles.get(0).getTitle()).isEqualTo("title");

        assertThat(articles.get(0).getContent()).isEqualTo("content");


        /*
            List 에서 <> 제네릭 Article 클래스의 객체를 요소로 담게 됨
            List 주요 메서드
                .get(인덱스 넘버) -> 0번째 인덱스에 저장된 Article 객체를 확인
            Article 객체의 필드는 title 과 content -> Getter 를 사용해서
            getTitle() 과 getContent()

            writeValueAsString : 객체를 JSON 으로 직렬화
            그 이후에 MockMvc 를 사용해 HTTP 매서드 , URL , 요청본문, 요청 타입 드응 ㄹ설정 뒤 테스트 요청보냄
            contentType() 메서드 : JSON/XML 등 다양한 타입 중 하나를 선택 할 수 있는데 일다 JSON 썻음
            assertThat() 메서드 : 글의 갯수가 1인지 ,
            Article 객체의 field 인 title 의 값이 "제목"인지
            Article 객체의 field 인 content 의 값이 "내용"인지 확인

        assertThat(articles.size()).isEqualTo(1);   - 블로그의 글 크기가 1이어야 합니다.
        assertThat(articles.size()).isGreaterThan(2);   - 블로그의 글 크기가 2보다 커야 합니다.
        assertThat(articles.size()).isLessThan(5);   - 블로그의 글 크기가 5보다 작아야 합니다.
        assertThat(article.title()).isEqualTo("제목");   - 블로그의 글의 title 값이 "제목"이어야 합니다.
        assertThat(article.title()).isNotEmpty();   - 블로그의 글의 title 값이 비어있지 않아야 합니다.
        assertThat(article.title()).contains("제");   - 블로그의 글의 title 값이 "제"를 포함해야 합니다.



         */


    }
    @DisplayName("findAllArticles : 블로그 글 목록 조회 성공")
    @Test
    public void findAllArticles() throws Exception{
        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }

    @DisplayName("findArticle : 블로그 글 조회 성공")
    @Test
    public void findArticle() throws Exception{
        //given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        //when
        Article savedArticle = blogRepository.save(Article.builder()
                        .title(title)
                        .content(content).
                        build());

        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));
    }
    /*
        삭제 구현
            blogservice 가서 delete 메서드 추가
     */
    @DisplayName("deleteArticle : 블로그 글 삭제")
    @Test
    public void deleteArticle()throws Exception{
        // given 1
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        // given 2
        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content).
                build());

        //when      -cr과 다른점 있음
        mockMvc.perform(delete(url, savedArticle.getId()))//findArticle 처럼 하나의 객체를 대상으로 할때는 argument가 url만 있는게 아니다
                .andExpect(status().isOk());

        List<Article> articles = blogRepository.findAll();
        // test  매서드는 메서드 단위로 실행됨

        assertThat(articles).isEmpty();

    }


}