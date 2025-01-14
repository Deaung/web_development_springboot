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
    학습 목표 : 로그인 / 회원가입 기능 구현
        회원 가입 구조는 로그인 구조와 유사함

        /login 요ㅕ청이 들어올때
        -> UserViewController 가 해당 요청에 대한 분기 처리를 하고 WebSecurityConfig 에 설정한 보안 관련
            내용물을 실행
        -> UserDetailsService 를 실행하면 요청을 성공했을 때
        -> defaultSuccessUrl 로 설정한 /articles 로 리다이렉트 하거나 csrf 를 disable 하거나 하는 등 작업

        UserDetailsService 에서는 loadUserByUsername() 메서드를 실행하여 이메일로 유저 찾고 변환

        여기서 유저는 User 클래스의 객체이고 UserRepository 에서 실제 데이터를 가져올 예정

        회원가입 역시 유사하게 구성

        로그아웃
        /logout 요청
        -> UserApiController 클래스 에서 로그아웃 로직 실행 예정
        그 로그아웃 로직 : SecurityContextLogoutHander 에서 제공하는 logout() 메서드 실행 예정

        1 스프링 시큐리티
            1) 인증 vs 인가
                (1) 인증 (Authentication) : 사용자의 신원을 입증하는 과정
                    예를 들어 사용자가 사이트에 로그인 할 때 누구인지 확인하는 과정 로그인이라 함

                (2) 인가 (Authorization) : 사이트의 특정부분에 접근 할 수 있는지 권한을 확인하는 작업
                    예를 들어 관리자는 관리자 페이지에 들어갈 수 있지만 일반 사용자는 관리자 페이지에
                    들어갈 수 없음

                인증과 인가 관련 코드를 아무런 도구 없이 작성하려면 복잡하기 때문에 스프링 시큐리티 사용

            2) 스프링 시큐리티
                : 스프링 기반 어플리케이션의 보안을 담당하는 스프링 하위 프레임워크 보안관련 욥션 다수 제공
                    CSRF, 세션 공격을 방어해주고 요청 헤더도 보안 처리를 해주므로
                    보안 관련 개발을 하는데 부담 덜어줌

            3) 필터 기반으로 동작하는 스프링 시큐리티
                : 스프링 시큐리티의 풀 로그인 과정 설정은 크게 어렵지 않다
                그러나 내부적으로는 꽤 복잡한 과정 거치게 됨

                외워야 하는것 X 어떤 흐름으로 이어지는지 이해하면 활용 가능
                로그인 관련 부분 코딩으로 이어감

        2. 회원 도메인 만들기
            스프링 시큐리티를 활용한 인증, 인가 기능 구현 예정
            회원 정보 저장할 테이블 만들고
            테이블과 연결할 도메인 만들고
            테이블과 연결할 회원 엔티티 만들고
            회원 엔티티와 연결되어 데이터를 조회하게 ㅎ해 줄 리포지토리가 추가,
            마지막으로 스프링 시큐리테어ㅔ서 사용자 정보 가져오는 서비스 구현

*/