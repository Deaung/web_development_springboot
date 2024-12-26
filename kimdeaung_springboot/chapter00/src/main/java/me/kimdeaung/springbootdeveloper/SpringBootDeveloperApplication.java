package me.kimdeaung.springbootdeveloper;
/*
    모든 프로젝트에는 main에 해당하는 클래스가 존재합니다-> 실행용 클래스
    이제 이 class 를 main 클래스로 사용할 예정
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }

}
 /*
    처음으로 SpringBootDeveloperApplication 파일을 실행시키면 whitelabel error page 가뜹니다.
    현재 요청에 해당하는 페이지가 존재하지 않기 때문에 생겨난 문제입니다
    -> 하지만 스프링 어플리케이션은 실행 됨

    그래서  error 페이지가 기분 나쁘니 기본적으로 실행 될 때의 default 페이지를 하나 만들고자 합니다.

    20241223 MON
        1.IntelliJ Community Version 설치 -> bin.PATH 체크 하고 나머지 디폴트
        2.Git 설치
        3.GitHub 연동 -> web_development_springboot -> 현재 문제가 있음
        4.IntelliJ에 Gradle 및 SpringBoot 프로젝트 생성
        5.POSTMAN 설치
  */