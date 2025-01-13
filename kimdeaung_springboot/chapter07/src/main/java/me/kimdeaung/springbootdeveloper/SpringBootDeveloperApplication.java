package me.kimdeaung.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  //created_at,update_at 자동 업데이트 해주는 에너테이션
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}
/*
    1. 사전 지식 : 타임리프(thymeleaf)
        타임리프는 템플릿 엔진 다만 템플릿 엔진은 HTML 과 템플릿 엔진을 위한 문법을 섞어서 사용
            HTML 지식 필요


        *템플릿 엔진 : 스프링 서버에서 데이터를 받아 우리가 보는 웹 페이지, HTML 상에서
            그 데이터를 넣어 보여주는 도구

        템플릿 엔진 개념 잡기
            간단한 템플릿 문법의 예
            <h1 text=${이름}>
            <p text=${나이}>

            h1 태그에는 ${이름}이 텍스트 어트리뷰트로 할당되어있음
            p 태그 또한 비슷한 상황
            이상이 템플릿 문법의 예시인데
            이렇게 해두면 서버에서 이름 나이라는 key  로 데이터를 템플릿엔진에 남겨주ㄴ고
            템플릿 엔진은 이를 HTML 에 값을 적용

            서버에서 보낸 예
            {
                이름 : "홍길동"
                나이 : 11
            }
            값이 달라지면 그때그때 화면에 반영하니 동적인 웹 페이지 만들 수있다

            템플릿 엔진은 각각 문법이 조금씩 다른 편
            그래서 템플릿 엔진마다 새로 배워야 하는 단점 존재
                대표적인 템플릿 엔진 예시 : JSP, 타임리프, 프리마컫 등등
+
                하지만 스프링 타임리프를 권장하고 있으므로 타임리프 사용 예정
                구체적 사용벙법은 실십으로 알아볼예정
                아하에서 소개하는 표현식들은 전달받은 데이터를 사용자들이 볼 수 이

                표현식
                $ 변수 값 표현식
                # 속성 파일 값 표현식
                @ URL 표현식
                *


 */