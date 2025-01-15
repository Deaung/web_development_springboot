package me.ahngeunsu.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}
/*
    1. 사전 지식 : 토큰기반 인증

    사용자가 서버에 접근 할 때 이 사용자가 인증된 사용자 인지 확인하는 방법중 하나
        대표적으로 서버 기반 인증과 토큰기반 인증이 있음

        토큰기반인증:
            *토큰 : 서버에서 클라이언트를 구분하기 위한 유일한 값

            서버가 토큰을 생성해서 클라이언트에게 제공, 클라이언트는 이 토큰 가지고 있다가
            여러 요청을 해당 토큰과 함께 신청, 이후 서버는 토큰을 확인하여 유효한 사용자인지 검증

            chapter08 에서는 스프링 시큐리티 이용하여 세션기반 인증을 사용해 사용자마다
            사용자의 정보를 담은 세션을 생성하고 저장해서 인증 -> 세션 기반 인증

            토큰을 전달하고 인증 받는 과정
                토큰은 요청과 응답에 함께 보냄

                1) 클라이언트가 아이디와 비밀번호 서버에 전달하며 인증
                2) 서버는 아이디와 비밀번호 확인, 유효한 사용자라면 생성하면서 응답
                3) 클라이언트는 서버에서 준 토큰을 저장
                4) 이후 인증이 필ㅇ한 API 사용시 토큰 함께 보맨
                5) 서버는 토큰이 유효한지 검증
                6) 토큰이 유효하다면 클라이언트의 요청하ㄴ내용을 처리

            토큰기반의 특징
                1) 무상태성
                    사용자의 인증 정보가 담겨있는 토큰이 서버가 아닌 다른 클라이언트에 있으므로
                        서버에 저장할 필요 없음
                    서버가 데이터를 유지하고 있으려면 그만큰 자원을 소비해야핟는데
                    토큰 기반 인증에서는 클라이언트에서 인증정보가 담긴 토큰 생성하고 인출하여
                    서버 입장에서는 클라이언트의 인증 정보를 저장하거나 유지할 필요가 ㄱ버
                    그래서 완전한 무상태성 효율적 검증 가능

                2) 확장성
                    무상태성. 서버를 확장할 때ㅔ 관리 심사를 신경쓸 필요 없음
                    세션 기반 인증은 각각 api 에서 인증을 해야하는것도 있고



                3) 무결성
                    토큰 기반 인증 방식은 HMAC 기법 이라고 함
                    토큰 발급 이후 토큰 정보르










































        2. jwp 서비스 구현
            의존성과 토큰 제공자를 추가하고 리프레시 토큰 필터를 구현하면
                ->> jwp 서비스 사용할 준비 완료
            bulid.gradle 이동

            토큰제공자 추가
                jwp 사용해서 JWT 를 생성하고 유효한 토큰인지 검증하는 클래스 추가 예정
                    JWT 토큰을 만들려면 이슈 발급자(issuer), 비밀키()



 */