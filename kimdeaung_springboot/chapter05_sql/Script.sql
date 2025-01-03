-- 주석 처리 하는 방법
-- 1. SELECT : 보여달라 / 조회해라 
select "Hello SQL";
-- select : 보여달라 / Hello SQL 이라는 str
select 12+7; -- sql 문을 통한 연산 가능

-- 결과 창의 첫 행에 각각 findl,insigth, with SQL 을 3개의 칸에
-- 걸쳐 순서대로 출력, 컬럼명 순서대로 퍼스트 세컨드 서드 출력

select 
	"Find" as 'First',
	"Insight" as 'second',
	"whit SQL" as 'Third';
-- 이런 식으로 작성하는 이유는 모두가 알아보기 쉽게 하기 위함
-- as : alias 알리아스, 데이터가 들어가는 부분에 대해서 칼럼에 대한 별칭을 붙일때 사용하는 구문
-- sql 문 특징 : 큰 따음표. 작은 따옴표 구분 안함 
-- 쉼표읠 역할 : 나열 할 때 사용

-- select 이용 28+891
-- select 이용 19*27
-- select 이용 다음3가지 결과 다른칼럼으로 결과창 표시
-- 37+172(plus)
-- 25*78
-- I love SQL
select 28+891;
select 19*27;
select 
	37+172 as "PLUS",
	25*78 as "Times",
	"I love SQL" as"Result";

-- 2.FROM : ~로 부터 + 테이블 명
-- FROM 은 데이터가 저장된 위치를 나타냄
select *
	from users;
-- users.csv 파일에 있던 모든 테이블과 컬럼과 값이 출력됨
-- * : asterisk = all : 와일드 카드라는 표시 // java
-- select * from users; : users 테이블 내에 있는 모든 컬럼의 값을 조회하라

-- 연습 제품정보 테이블 products 에 있는 모든 데이터 출력
select *
	from products;

-- 한줄 작성도 가능 -> 긴 쿼리문을 쓸 때는 가독성 때문에 개행을 한다
-- 연습을 위해 한줄 작성할 때도 있고 개행 할때도 있을예정
-- 쿼리문 명령어 해당부분은 가독성 때문 대문자 사용 예정이였으나
-- 대 소문자를 구분하지 않는다.
-- LIMIT : 갯수 제한 거는 명령어 (어떤 IDE 를 쓰느냐에 따라 TOP 일 때도 있음)
select * from products limit 3;
-- 이런식으로 * 사용해서 전체 정보 조회하는 것을 fullscan 이라 한다 / 빈도 높지 않다.

-- 개수 제한을 걸기 위한 limit 와 ㅣ특정 컬럼 조회하는 형태로 수업 진행
-- SELECT 컬럼명 1, 컬럼명2 FROM 테이블 명;
-- 가격 할인가 컬럼 10개
select id , name from products;
select 
	price, discount_price 
	from PRODUCTS 
	limit 10;
-- SQL 문의 경우 순서가 명확하게 정해져있음 
-- 읽기는 쉽지만 작성하기 까다로울 수 있다.

-- orderdetails 의 모든 정보 표시
SELECT *
	FROM ORDERDETAILS;


-- 회원정보 테이블 users 에서 상위 7건 표시
SELECT *
	FROM USERS LIMI
sleT 7;S;
