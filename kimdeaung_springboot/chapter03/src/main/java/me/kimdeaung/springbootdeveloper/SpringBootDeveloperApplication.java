package me.kimdeaung.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
    1. 스프링 부트 3 구조 살펴보기
        각 계층이 양 옆의 계층과 통신하는 구조
        계층 : 각자의 역할과 책임이 있는 어떤 소프트웨어의 구성요소
            -> 각 계층은 소통할 수는 있지만 다른 계층에 간섭하거나 영향을 미치지는 않음

        ex) 카페와 빵집
            카페에서는 커피를 팔고 빵집에서는 빵을 파는데 필요한 경우 서로 협업 관계를 맺어
            어떤 손님이 커피를 사면 빵을 할인 해 줄 수는 있음 -> 각 계층 간 소통
            그러나 빵집 직원이 커피를 서빙 할 수는 없음

        스프링 부트에서의 계층
            1) 프레젠테이션(Presentation)
                HTTP 요청을 받고 이 요청을 비지니스 계층으로 전송하는 역할 -> Controller 가
                        프레젠테이션 계층 역할
                TestController 클래스와 같은것을 의미하며 스프링 부트 내에 여러개가 있을 수 있음
            2) 비지니스(Business)
                 모든 비지니스 로직을 처리
                    *비지니스 로직 : 서비스를 만들기 위한 로직을 의미하며  웹 사이트에서 벌어지는
                    모든 작업 -> 주문 서비스라고 한다면 주문 개수, 가격 등의 데이터를 처리 하기 위한
                    로직, 주문 처리를 하다가 발생하는 예외처리 로직, 주문을 받거나 취소하는
                    등 프로세스를 구현하기 위한 로직 등 을 의미함

            3) 퍼시스턴스(persistence)
                모든 데이터베이스 관련 로직을 처리. 이 과정에서 데이터 베이스에 접근하는
                    DAO객체를 사용할 수도 있음.
                        *DAO - 데이터 베이스 계층과 상호작용하기 위한 객체라 이해
                        -> Repository가 퍼시스턴스 계층의 역할

            계층은 계념의 영역이고 Controller, Service, Repository 는 실제 구현을 위한 영역

        스프링 부트 프로젝트 디렉토리 구성
            : 스프링 부트에는 정해진 구조는 없지만, 추천 프로젝트 구조가 있고 이를 많은 개발자들이 따르니
            수업시에도 해당구조 따라 개발예정

            main :  실제 코드를 작성하는 공간 프로젝트 실행에 필요한 소스 코드 및 리소스파일 모두 이폴더에

            test : 프로젝트 소스코드를 테스트 할 목적의 코드나 리소스 파일 들어있음
             (설정에 따라 자동생성이 안될수 있음)

            build.gradle : 빌드를 설정하는 파일. 의존성이나 플러그인 설정과 같이 빌드에 필요한 설정을 할 때 사용

            settings.gradle : 빌드할 프로젝트의 정보를 설정하는 파일

            지시 사항
            main 디렉토리 구성하기
                : main 디렉토리 내에 java 와 resources 로 구성되 있음. 여기에 아직 추가하지 못했던 스프링 부투
                프로젝트이 구성요소를 추가

                01단계 - HTML 과 같은 뷰 관련 파일(chapter07 수업 예정)을 넣을 templates 디렉토리
                    resources 우클릭 -> new directory -> templates

                02단계 - static 디렉토리 JS,CSS, 이미지와 같은 파일 넣는 용도 사용

                03단계 - 스프링 부트 설정을 할 수 있는 application.yml 파일을 생성
                -> 해당 파일은 스프링 부트 서버가 실행 되면 자동으로 로딩되는 파일로,
                데이터베이스의 설정 정보, 로깅 설정 정보 등이 들어갈 수도 있고 직접 정의 할때
                사용하기도 함. 앞으로 프로젝트 진행하며 자주 사용할 파일
                resources 우클릭 -> new file -> application.yml

    2. 스프링 부트 3프로젝트 발전 시키기

        각 계층의 코드를 추가 할 예정 -> 이를 통해 계층이 무엇이고, 스프링 부트에서는 계층을 어떻게
        나누는지 감을 잡도록 수업
        지금은 의존성 추가 -> 프레젠테이션 계층, 비지니스 계층 , 퍼시스턴스 계층 순서로
        코드 추가할 예정


        implementation : 프로젝트 코드가 컴파일 시점과 런타임 모두 해당 라이브러리를 필요로 할 때 사용
        testImplementation : 프로젝트의 테스트 모드를 컴파일 하고 실행 할 때만 필요한 의존성 설정
        테스트 코드에서만 사용 메인 어플리케이션에는 사용안함
        runtimeOnly:  런타임에만 필요한 의존성을 지정 컴파일 시에는 필요하지 않지만
        어플리케이션을 실행할 때 필요한 라이브러리 설정
        compileOnly: 컴파일 시에만 필요 런타임에는 포함되지 않아야 하는 의존성 지정
        annotationProcessor : 컴파일 시에 애너테이션을 처리 할 때 사용하는 도구의 의존성 지정

    지시사항
        프레젠테이션 서비스 퍼시스턴스 계층 만들기

            1. 프레젠테이션 계층에 속하는 컨트롤러 관련 코드 작성 -> TestController 가 있으므로 생략
            2. 비지니스 계층 코드 -> TestController 와 같은 위치에 TestService.java 생성
            3. 퍼시스턴스 계층 코드 작성 -> 같은 위치에 Member.java 생성 -> 실제 DB에 접근하는 코드 작성
            4. 매핑 작업에는 '인터페이스 파일 필요. MemberRepository 인터페이스를 같은위치에 생성


    작동 확인

        01. resources 폴더에 sql 문 하나 추가
            resources 우클릭 -> new -> file -> data.sql

        02. 이제는 기존에 만들어 둔 application.yml 수정

        03. 서버 실행 후 ctrl + f 눌러서 create 검색 후 table 생성 되었는지 확인

        04. postman 에서 HTTP 요청 시도
            1) 포스트 맨 실행
            2) HTTP 매서드 get 으로 설정 후 url에
                http://localhost8080/test
            3) send 버튼 누름
            4) 200ok 인지 확인

    http 요청---> testController   --    TestService -- memberRepository -- database
    url:/test -- 프레젠테이션 계층    --    비지니스 계층     퍼시스턴스 계층     데이터베이스


    chapter04 프로젝트 생성

 */

@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }

}
