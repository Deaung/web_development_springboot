package me.kimdeaung.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
    1. 사전 지식 : API 와 REST API
        API : 프로그램 간의 상호 작용을 위한 매개체

        식당으로 알아보는 api
            식당에 들어감 --> 점원에게 요리를 주문함 -->
            점원은 주방에 요리를 만들어달라 요청
                요청      요청
            손님<--> 점원 <--> 주방
                응답      응답
            의 형태를 띄고 있음, 손님 역할을 클라이언트(client), 주방의 요리사를 서버(server)
            중간의 점원을 API 라고 생각 --> 매개체

            우리는 웹 사이트 주소를 입력해서 '구글 메인 화면 보여줘' 라고 요청하면 API 는 이 요청을 받아
            서버에 전달, 서버는 API 가 준 요청을 처리해 결과물을 구성해 다시 API 로 반환하고 API 는 최종
            결과물을 브라우저에 보내줌
                API 는 클라이언트의 요청을 서버에 전달, 서버의 결과물을 클라이언트에 잘 돌려주는 역할

                REST API??
            웹의 장점을 최대한 활용하는 형태
                Representational State Transfer 의 줄임말 자원을 이름으로 구분해 자원의 상태를 주고받는 API
                URL 의 설계방식

                특징 :
                    REST API 는 서버/클라이언트 구조, 무상태, 캐시 처리 가능, 계층화, 일관성과 같은 특징

                장점 :
                    URL 만 보고도 무슨 행동을 하는 API 인지 알 수 있음
                    무상태 특징으로 인해 클라이언트와 서버의 역할이 명확하게 분리
                    HTTP 표준을 사용하는 모든 플랫폼에서 사용 가능

                단점 :
                    HTTP 메서드 즉 GET,POST 와 같은 방식의 개수에 제한있음
                    설계 위한 공식적으로 제공되는 표준 규약 없음

                장단점 고려 했을 때 주소와 HTTP 메서드만 보고 요청의 내용을 파악가능하다는 장점으로
                REST 하게 디자인한 API 를 보고 RESTful API 라고 부르기도 함

                REST API 사용법
                    규칙 1 URL 에는 동사 사용 않고 자원을 표시
                        * 자원 : 가져오는 데이터를 의미 예를 들어 학생중 id 가 1인 학생의 정보를 가져오는
                            url 은
                            1) /student/1
                            2) /get-student?student_id=1
                        같은 방식으로 설계가능

                    이중 REST API 에 맞는 형식은 1) 예시에 해당, 2)예시의 경우 자원이 아닌 다른 표현을 섞어 사용했기에

                    동사를 사용해서 생기게 되는 추후 문제점을 예시 -> 데이터 요청하는 URL 설계시
                        A 개발자는 get. B 개발자는 show를 쓰면 get-student , show -data 등으로 협의가 이뤄지지 않은
                        설계가 될 가능성 있음
                        RSETFUL API는 동사 사용 안함

                    규칙 2 동사는 HTTP 메서드
                        HTTP 메서드 : 서버에 요청하는 방법을 나누는것 --POST GET PUT DELETE
                        만들고 읽고 업데이트 하고 삭제한다 CRUD 라고도 함
                        예를 들어 블로그에 글 쓰는 설계를 한다면

                            1) id가 1인 블로그 글을 조회하는 api : GET/articles/1
                            2) 블로그 글 추가하는 api : post/articles
                            3) 블로그 글 수정하는 api : put/articles/1
                            4) 블로그 글 삭제 하는 api : DELETE/articles/1\

                        *GET/POST 등은 URL 에 입력하는 값이 아닌 내부적으로 처리하는 방식을 미리 정하는것
                        실제 HTTP 메서드는 내부에서 서로 다른 함수로 처리 대놓고 적는일은 잘 없다

                        이 외에도 '/' 는 계층 관계를 나타내는데 사용하거나 밑줄 대신 하이픈을 사용하거나 자원의
                        종류가 컬렉션인지 도큐먼트 인지 에 따라 단수 복수로 나누거나 하는 등 규칙이 있으나 추후 설명 예정

                2. 블로그 개발을 위한 엔티티 구성
                    이번 프로젝트

                    엔티티 구성
                    엔티티와 매핑되는 테이블 구조는 ↓
            +-------------------------------------------------------------+
            | 컬럼명  | 자료형        | null 허용 |  키   |       설명       |
            +-------------------------------------------------------------+
            | id     | BIGINT       |     N     | 기본키 | 일련번호, 기본 키 |
            ---------------------------------------------------------------
            | title  | VARCHAR(255) |     N     |       | 게시물의 제목     |
            ---------------------------------------------------------------
            | content| VARCHAR(255) |     N     |       | 내용             |
            +--------------------------------------------------------------+


 */
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}