package me.kimdeaung.springbootdeveloper;
/*
    New project 생성 시 주의점:
        1. build system -> Gradle 설정
        2. DLS -> Groovy 설정
        3. name = ArtifactID
        4. build.gradle 설정을 복사하는데, 복사 후에 -> sync 를 반드시 눌러주어야 한다
            ->SpringBootDeveloperApplication 에서 @SpringBootApplication 에너테이션에
            빨간 줄 가있다면 새로고침 해봐야함.
        5. resources 내에 static 내에 index.html 이라 하는데,해당 폴더명에 경우 대부분의 개발자가 합의한 형태
        6. 앞의로의 수업 시간에는 new project 생성시에 이렇게 자세히 풀이할 일은 자주 있지 않을 예정, 꼭 익혀두기
            ->github 본다고 되는 부분 아니니 만큼 신경 써야함.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
    1. Spring vs. SpringBoot

        Enterprise Application : 대규모의 복잡한 데이터를 관리하는 애플리케이션
            많은 사용자의 요청을 동시에 처리해야 하므로 서버 성능과 안정성, 보안 이슈등
            모든 걸 신경쓰면서 사이트 기능, 즉 '비지니스 로직' 을 계발하기가 매우 어려워서
            등장하게 된 것이 Spring

        SpringBoot 의 등장
            스프링은 매우 많은 장점을 지니고 있지만 설정이 복잡하다는 단점이 있음
            이런 단점을 보완하고자 등장한 것이 스프링 부트

            특징 :
                1) 톰캣, 제티, 언더토우 같은 웹 애플리케이션 서버가 내장되어 있어 따로 설치하지 안하도
                    독립적 실행 가능
                2) 빌드 구성을 단순화하는 스프링 부트 스타터를 제공
                3) XML 설정을 사용하지 않고 자바 코드로 작성 가능
                4) JAR 를 이용하여 자바 옵션만으로 배포 가능
                5) 애플리케이션 모니터링 및 관리 도구인 스프링 액츄이에이터(Spring Actuator) 제공

            즉, 스프링부트 라는것은 기본적으로 스프링에 속해 있는 도구중 하나

            차이점 :
                1) 구성의 차이 -> 스프링은 애플리케이션 개발에 필요한 환경을 수동적으로 구성하고 정의
                    하지만 스프링 부트는 스프링 코어와 스프링 MVC 의 모든 기능을 자동으로 로드
                    수동으로 개발환경 구성할 필요 없음

                2) 내장 WAS 유무 -> 스프링 애플리케이션은 일반적으로 톰캣괖 같은 WAS 에서 배포
                    WAS : Web Application Server 의 약자
                    하지만 스프링 부트는 자체적으로 WAS 를 가지고 있어 JAR 파일만 만들면
                    별도로 WAS 설정 하지 않아도 에플리케이션 실행 할 수 있음

            2. 스프링 컨셉
                1) 제어의 역전(IoC)
                    Inversion of Control : 다른 객체를 직접 생성하거나 제어하는 것이 아니라
                    외부에서 관리하는 객체를 가져와 사용하는것
                    ex) 클래스 A에서 클래스 B의 객체를 생성한다고 가정했을 경우의 자바코드
                    public class A{
                        B b = new B():
                    }

                    위와는 다르게 클래스 B의 객체를 직접 생성하는것이 아닌 어딘가에서 받아와 사용
                    실제로 스프링은 스프링 컨테이너에서 객체를 관리 제공하는 역할을 함
                    ex) 스프링 컨테이너가 객체를 관리하는 방식의 자바코드
                    public class A{
                        private B b;
                    }

                2)의존성 주입(DI)
                    Dependency Injection : 어떤 클래스가 다른 클래스에 의존한다는 의미

                    @Autowired 애너테이션이 중요 -> 스프링 컨테이너에 있는 빈(Bean) 을 주입하는 역할
                    Bean : 스프링 컨테이너가 관리하는 객체 -> 추후 재 설명

                    ex) 객체를 주입받는 모습 예
                    public class A{
                        @Autowired
                        B b;
                    }
                3) 빈과 스프링 컨테이너
                    3)-1 스프링 컨테이너  : 빈이 생성되고 소멸 되기까지의 생명주기를 관리함
                        또한 @Autowired 와 같은 애너테이션을 사용해 빈을 주입받을 수 있게 DI를 지원함

                    3)-2 빈 : 스프링 컨테이너가 생성하고 관리하는 객체 -> 이상의 코드들에서 B가 빈에 해당
                        스프링은 빈을 스프링 컨테이너에 등록하기 위해 XML 파일 생성, 애너테이션 추가 등의
                        방법을 제공하는데, 이 것이 의미하는 바는
                            1- 빈을 등록하는 방법은 여러가지다
                            2- 수업 중 우리가 사용하는 방식은 애너테이션이다
                        ex) 클래스를 빈으로 등록하는 방법 예제
                        @Component
                        public class MyBean()

                            이상과 같이 @Component 라는 애너테이션을 붙이면 MyBean 클래스가 빈으로 등록됨
                            이후 스프링 컨테이너에서 이 클래스를 괸리하게 되고, 빈의 이름은 첫 문자를
                            소문자로 바꿔서 관리. 즉 클래스 MyBean 의 이름은 myBean 이 됨.

                            일반적으로 스프링이 제공해주는 객체로 받아들이시면 됨

                4) 관점 지향 프로그래밍(AOP)
                    Aspect-oriented Programming : 프로그래밍에 대한 관심을 핵심관점 / 부가 관점으로
                    나누어서 모듈화 함을 의미

                    ex). 계좌 이체, 고객 관리하는 프로그램이 있다고 가정할때 각 프로그램에는
                        로깅 조직 즉 지금까지 일어난 일을 기록하기 위한 로직과 여러 데이터를 관리하기 위한
                        데이터 베이스 연결 로직이 포함됨. 이 때,
                            핵심관점 - 계좌 이체, 고객 관리 로직
                            부가 관점 - 로깅, 데이터 베이스 연결 로직

                5) 이식 가능한 서비스 추상과(PSA)
                    Portable Service Abstraction : 스프링에서 제공하는 다양한 기술들을 '추상화'
                        해 개발자가 쉽게 사용하게 하는 '인터페이스'

                    ex) 클라이언트의 매핑 / 클래스, 메서드의 매핑을 위한 애너테이션들
                    스프링에서 데이터 베이스에 접근하기 위한 기술로 JPA, MyBatis, JDBS 등
                    어떤 기술을 사용하든 일관된 방식으로 데이터 베이스에 접근하도록 인터페이스를 지원
                    WAS 역시 PSA 의 예시중 하나로 어떤 서버를 사용하더라도(톰캣, 언더토우, 네티 등)
                    코드를 동일하게 작성 가능


3. 스프링 부트 3 둘러보기
    첫 번째 스프링 부트 3 예제 만들기
        01단계
            springbootdeveloper 패키지에


    스프링 부트 스타터 살펴보기

        스프링 부트 스타터는 의존성이 모여있는 그룹에 해당함.
        스타터를 사용할 경우 필요한 기능을 간편하게 설정 가능
        스타터의 명명 규칙
            spring-boot- starter-{작업유형}

        자주 사용하는 스타터의 예시
        spring-boot-starter-web : Spring MVC 를 사용해 RESTful 웹 서비스를 개발 할 때 필요한 의존성 모음
        spring-boot-starter-test : 스프링 애플리케이션을 테스트 하기 위해 필요한 의존성 모음
        spring-boot-starter-validation : 유효성 검사를 위해 필요한 의존성 모음
        spring-boot-starter-actuator : 모니터링을 위해 애플리케이션에서 제동하는 다양한 정보를
            제공하기 쉽게 하는 의존성 모음
        spring-boot-starter-jpa : ORM 을 사용하기 위한 인터페이스의 모음인
            JPA 를 더 쉽게 사용하기 위한 의존성 모음

    스프링 부트 3와 자바 버전
        스프링 부트 3는 자바 17버전 이후부터 가능

 */
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }

}
