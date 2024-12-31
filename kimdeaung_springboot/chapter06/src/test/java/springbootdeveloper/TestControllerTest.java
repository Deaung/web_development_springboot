package me.kimdeaung.springbootdeveloper;
/*
    test 클래스 생성방법

    1/ 테스트 하고자 하는 클래스 (main/java 내에 있는) 를 연다
    2. public class 클래스 명이 잇는 클래스 명 클릭
    3. alt + enter 누르기
    4. create test 선택
    5. 현재 프로젝트 상으로 JUnit5 로 고정
 */
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;
    //위는 객체 생성 아래는 메서드

    @BeforeEach
    public void mockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build(); // builder.build 의 응용

    }

    @AfterEach
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception { //throws Exception : 예외로 처리하다
        //given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L,"홍길동"));
        //maybeags/web_development_java 에 c15_casting 에 Central =

        //web
        final ResultActions result = mockMvc.perform(get(url)     //(1)
                .accept(MediaType.APPLICATION_JSON));             //(2)

        //then
        result.andExpect(status().isOk())                         //(3)
                //                                                  (4)
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }

}
/*\
    (1) : perform() : 메서드는 요청을 전송하는 역할을 하는 메서드
        return 값으로 ResultActions 객체를 받음
        ResultAction 객체는 반환값을 검증하고 확인하는 andExpect() 메서드를 제공

    (2) : accept() : 메서드는 요청을 보낼 때 무슨 타입으로 응답을 받을지 결정하는 메서드
        주로 JSON 사용예정

    (3) : andExpect() 메서드는 응답을 검증. TestController 에서 만든 API는 응답으로
        OK(200) 을 반환하므로 이에 해당하는 메서드만 isOk를 사용해 응답 코드가 200(OK)인지 확인

    (4) : jsonPath("$[0].{필드명}")은 json 응답값(value)을 가져오는 역할을 하는 메서드
        0(인덱스)번째 배열에 들어있는 객체의 id,name 값을 가져오고 저장된 값과 같은지 확인
        (memberRepository.saveMember.getId()등등 이용)
 */
/*
    @SpringBootTest
        어플리케이션 클래스에 추가하는 에너테이션 @SpringBootApplication 이 있는 클래스를 찾고 그 클래스에 포함되어있는
        빈을 찾은 후 테스트용 어플리케이션 컨텍스트 라는 것을 생성

    @AutoConfigureMockMvc
        MockMvc 를 생성하고 자동으로 구성하는 애너테이션
        어플리케이션을 서버에 배포하지 않고도 테스트용 Mvc 환경을 만들어 요청 전송 응압 기능을 제공하는 클래스
            '컨트롤러를 테스트 할 때 사용되는 클래스'
 */