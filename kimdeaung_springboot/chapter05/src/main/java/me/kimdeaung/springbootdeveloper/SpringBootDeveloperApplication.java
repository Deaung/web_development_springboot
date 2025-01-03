package me.kimdeaung.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
5장. 데이터베이스 조작이 편해지는 ORM
    1. 데이터베이스 (database)
        데이터를 효율적으로 보관하고 꺼내볼 수 잇는 곳.

        DBMS(DataBaseManagementSystem)
            기본적으로 데이터베이스는 많은 사람이 공유 할 수 있어야하고, 동시 접근이 가능해야 하는 등 많은 요구사항이 존재한다.
                이를  만족시키면서도 효율적으로 데이터베이스를 관리하는 계체가  DBMS
                대부분 개발자들이 편하게  DB 라고 말하는 MySQL, Oracle, DBeaver 와 같은 것들 또한 DBMS

            관계형 DBMS
                Relational DBMS 를 줄여서 RDBMS 라고 부른다. 테이블 형태로 이루어진 데이터 저장소.
                RDBMS 는 테이플 형태로 이루어진 데이터 저장소
                각 행은 고유의 키, 즉 id를 가지고 있고 이메일 나이와 같은 회원과 관련된 값들이
                들어간다

                회원 테이블
                1열           2열           3열
               +----------------------------------+
               | ID         | 이메일       | 나이  | - header / column
               ------------------------------------
               | 1          | a@test.com  | 10    | - 1행
               | 2          | b@test.com  | 20    | - 2행
               | 3          | c@test.com  | 30    | - 3행
               +----------------------------------+
                기본키(PK) :
                Prime Key

            H2, MySQL
                해당 수업에서 상용할 RDBMS 는 m2, MySQL 두개
                H2 자바로 작동되어 있는 RBDMS : 스프링 부트가 지원하는 메모리 관계형 데이터 베이스
                    데이터를 다른 공간에 따로 보관하는 것이 아닌 어플리케이션 자체내부에 저장 하는 방식
                    그래서 어플리케이션 재 실행시 데이터는 초기화됨(서버 재실행)
                    간편하게 사용하기 좋음 테스트 용도로 사용
                    실제 서비스는 사용 안함
                MySQL - 실제 서비스로 돌릴 때 사용할 RDNMS 추후 수업예정

            필수 용어
                1. 테이블 : db 에서 데이터를 구성하기 위한가장 기본적 단위
                    행과 열로 구성되며 행은 여러 ㅣ속성으로 이어져 있다
                1. 행(row) : 테이플의 구성요소 중 하나이며 테이블의 가로로 배열된 데이터의 집합을 의미
                    행은 반드시 고유한 식별자인 기본키를 가진다 행을 record라고 부름
                3. 열(column) : 테이블의 구성요소 중 하나, 행에 저장되는 유형의 데이터
                    예로 회원 테이블이 있다고 할 때 열은 각 요소에 대한 속성을 나타냄(java는 안됨)
                    무결성을 보장, 이상의 표를 기준으로 할 때는 이메일은 문자열 나이는 숫자유형을 가짐
                    이메일에 숫자 혹은 나이열에 문자열 들어갈 수 없기 때문에 데이터 무결성 가짐
                4. 기본키 : 행을 구분할 수 있는 식별자 이값은 테이블에서 유일 해야 하며 '중복값을 가질 수 없음'
                    보통 데이터를 삭제하거나 수정하고 조회할때 사용 다른 테이블과 관계를 맺어 데이터 가져오기 가능
                    기본키의 값은 수정되면 안되며 유효한 값이여야 함 null 불가
                5. 쿼리 : 데이터베이스에서 데이터를 조회하거나 삭제 생성 수정과 같은 처리를 위해 사용하는 명령문
                    SQL 이라는 데이터 베이스 전용 언어 사용하여 작성

                    SQL: 구조화된 질의 언어

    ORM?
        Object-Relational Mapping 자바의 객체와 데이터베이스를 연결하는 프로그래밍 기법
            예로 데이터베이스에 age, name 컬럼에 20, 홍길동이라는 값이 있다고 가정했을 때 이것을 자바에서 사용하려면
            SQL 을 이용하여 데이터를 꺼네 사용하지만 ORM 이 있다면 데이터베이스의 값을 객체처럼 이용 가능
            SQL 에 어려움을 겪는다 해도 자바 언어로만 데이터 베이스에 접근해서 원하는 데이터 받아 올 수 있는 방식
            객체와 데이터베이스를 연결해 자바 언어로만 데이터베이스를 다룰 수 있도록 하는 도구 ORM


        장점:
            1. SQL을 직접 작성하지 않고 사용하는 언어(java)로 데이터베이스 접근 가능
            2. 객체지향적으로 코드를 작성할 수 있기 때문에 비지니스 로직에만 집중가능
            3. 데이터베이스 시스템이 추상화되어 있기 때문에 MySQL 에서 PostgreSQL 로 전환하더라고 추가작업이 거의 없음
            4. 매핑하는 정보가 명확하기 때문에(db와 인스턴스 변수의 값) ERD에 대한 의존도 낮추고 유지보수 편함

        단점:
            1. 프로젝트의 복잡성이 커질수록 사용 난이도 올라감
            2. 복잡하고 무거운 쿼리는 ORM 으로 해결이 불가능한 경우가 있음(JOIN 이 여러번 돌아가거나 / 서브쿼리 복잡한 경우)

        스프링데이터 JPA 에서 제공하는 메서드 사용
                MemberRepository.java 파일에서 create test
 */
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}