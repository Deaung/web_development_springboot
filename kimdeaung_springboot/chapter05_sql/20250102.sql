SELECT * FROM USERS;
-- WHERE : 어떤 조건에 합치하는 것을 고를것인가(조건문이랑 비슷)
-- 이전까지는 테이블 전체 혹은 특정 컬럼에 관련된 부분 조회했으나
-- 현업에서는 데이터 일부 컬럼을 가져오거나 상위 n의 데이터만 조회하는것
-- 뿐만 아니라 특정 칼럼의 값이 a인 데이터만 가져오는등 복잡한 쿼리를 작성할 필요가 있다

-- 회원정보 테이블 users에서 거주 국가 (country)가 한국인 회원만 추출하는 쿼리

SELECT * FROM  USERS WHERE COUNTRY = "Korea";

-- 거주국가가 한국이 아닌 회원만 추출하는 쿼리문

SELECT * FROM  USERS WHERE COUNTRY != "Korea";

-- != : ~가 아닌

-- 거주국가가 한국이면서 회원아이디가 10인 회원만 추출
SELECT * 
	FROM USERS 
	WHERE COUNTRY = "Korea" AND ID = "10"; -- 어차피 아이디는 중복없음(pk값)
	
-- where 절에서는 여러 조건을 동시에 적용가능 조건 갯수의 제한 없음
-- 논리연산 and/or/between

-- 예제 : 회원 정보 테이블 users 에서 가입 일시가 
	-- 2010-12-01 부터 2011-01-01 까지 회원만 출력

SELECT * 
	FROM USERS 
	WHERE CREATED_AT BETWEEN "2010-12-10" AND "2011-01-01"; 

-- where 절 작성에 있어서 컬럼명 먼저 나오고 =.!=,between 등 을 적용
-- select + from테이블명 + where
-- between : 시작과 종료 값을 포함하는 범위 내의 데이터를 조회
--			시간 값을 조회 할때는 컬럼명 between 시작날짜 and 종료날짜
--			마찬가지로 시작날짜와 종료날짜를 포함한 모든것을 출력

-- 예 : 가입일시가 2010-12-01 부터 2011-01-01 까지 회원정보 출력
-- between 사용 안하고 
SELECT *
	FROM USERS 
		WHERE CREATED_AT >="2010-12-01" AND CREATED_AT <="2011-01-01";
-- between 없이 한 이유 : between a and b 구문이
-- 초과/미만이 아니라 이상/이하 임을 증명하기 위해

-- 응용 users 에서 거주국가가 korea usa uk 인 회원정보를 추출
SELECT *
	FROM USERS 
	WHERE COUNTRY = "korea" OR COUNTRY = "usa" OR COUNTRY = "uk"
	;

-- users에서 거주국가가 korea usa uk 가 아닌 회원들 추출
SELECT *
	FROM USERS
	WHERE COUNTRY IN ("korea","usa","uk")
	;
SELECT *
	FROM USERS
	WHERE COUNTRY NOT IN ("korea","usa","uk")
	;
-- like :  해당 전치사 뒤의 작은 따옴표/큰 따옴표 내에서는 와일드카드 사용가능
-- sql 을 해석하는 컴퓨터가 like 코드 읽는 순간 와일드카드 감지하는데 
-- sql 에서는 와일드 카드를 % 로 0개 이상의 임의의 문자열을 의미하는 
-- 메타 문자로 사용됨

-- 예제 users 에서 country 의 이름이 s로 시작하는 회원정보 출력

 SELECT *
 	FROM USERS
	WHERE COUNTRY LIKE "S%"
	;
 -- 거주국가가 s 로 시작하는 국가 모두 출력
 
 -- 거주국가 s 로 끝나는 국가 모두 출력
  SELECT *
 	FROM USERS
	WHERE COUNTRY LIKE "%S"
	;
 -- 거주국가 명에 s 들어가면 출력
  SELECT *
 	FROM USERS
	WHERE COUNTRY LIKE "%S%"
	;
-- 응용 : users에서 country 이름이  s로 시작하지 않는 회원 출력
SELECT *
	FROM USERS 
	WHERE COUNTRY NOT LIKE "S%"
	;
 
-- IS : a is b =a는 b 다
 -- a 컬럼의 값이 b 일때(특히 null) = 대신 사용
-- 예제 : users 에서 created_at 칼럼의 값이 null인 값
SELECT *
	FROM USERS 
	WHERE CREATED_AT IS NULL;
SELECT *
	FROM USERS 
	WHERE CREATED_AT IS NOT NULL;

-- 연습문제
	-- 1 users에서 country가 mexico 인 회원정보 추출
		-- 단 created_at phone city contry 컬럼만 출력
SELECT 
	created_at,phone,city,country 
	FROM USERS
	WHERE COUNTRY = "mexico"
	;
	
	-- 2 orders에서 id 가 20이하 price는 30 이상인 제품정보 출력
		-- 단 기존 컬럼 전부 출력 및 정상가격에서 얼마나 할인되었는지 discount_amount 컬럼명 출력
SELECT * , -- 전부 다의 기준은 기존컬럼
	(price - DISCOUNT_PRICE) AS "discount_amount" -- 새로운 컬럼
	FROM PRODUCTS
	WHERE id <="20" AND price >="30"
	;

	-- 3 users 에서 contry 가 korea cannada belgium 도 아닌 회원 정보 or 사용 없이 출력
SELECT *
	FROM USERS
	WHERE COUNTRY NOT IN ("korea","cannada","belgium");
	
SELECT *
	FROM USERS
	WHERE COUNTRY != "korea" AND COUNTRY != "cannada" AND COUNTRY !="belgium"
	;

	-- 4 products 에서 name이 n 으로 시작하는 제품 정보 출력
		-- id name price 만 출력
SELECT 
	id,name,price
	FROM PRODUCTS
	WHERE name LIKE "n%";

	-- 5 orders 에서 order_date가 2015-07-01 2015-10-31 까지 아닌것만 출력
SELECT *
	FROM ORDERS
	WHERE ORDER_DATE NOT BETWEEN "2015-07-01" AND "2015-10-31"
	;
	
-- not between 순서
	
-- order by
	-- 현재까지  where을 이용하여 특정 조건에 합치하는 데이터 조회하는  sql 문 학습
	-- 이상의경우 저장된 순서대로 정렬된 결과만 확인 가능 -> pk값을 통해 확인(id)
	
	-- 가져온 데이터를 원하는 순서대로 정렬하는 방법
	
	-- users에서 id 기준으로 오름차순

SELECT *
	FROM USERS
	ORDER BY ID ASC; -- ASC : Ascending의 의미 오름차순이라는 뜻
	
	-- users에서 id 기준으로 내림차순
	
SELECT *
	FROM USERS
	ORDER BY ID DESC; -- DESC : Descending의 축어 내림차순이라는 뜻
	
-- 이상에서 볼 수 있듯 order by 는 컬럼을 기준으로 행 데이터를 asc 혹은 besc 로 정렬
	-- 숫자의 경우 쉽지만 문자의 경우 아스키코드(ASCII CODE)를 기준으로 함
	
	-- 예제  : users에서 city 기준으로 내림차순
SELECT *
	FROM USERS
	ORDER BY CITY DESC ;
	-- 예제  : users에서 created_at 기준으로 오름차순
SELECT *
	FROM USERS
	ORDER BY CREATED_AT aSC ;
	
	-- 예제 : users에서 첫 번째 컬럼 기준으로 오름차순 정렬 출력
SELECT *
	FROM USERS
	ORDER BY 3 ASC ;
	
-- order by 의 특징 컬럼명 대신에 컬럼 순서를 기준으로 하여 정렬이 가능
	-- 컬럼 명 몰라도 정렬 가능한 장점있으나 주의 필요
	
	-- 예제 : users 에서 username, phone,city,country,id 칼럼 순서대로 출력/첫번째 컬럼 기준으로 오름차순
SELECT 
	username,phone,city,country,id 
	FROM USERS
	ORDER BY 1 ASC
	;
	-- 아까와 같이 order by 1 asc 로 했으나 정렬이 id 가 아닌 username 으로 적용 정렬방식이 달라짐
	-- order by 는 데이터 출력이 끝난 후로 적용됨
	
-- 예제 : users 에서 city, id 컬럼만 출력 city 기준 내림차순
	-- id 기준 오름차순 정렬
SELECT
	city, id
	FROM USERS
	ORDER BY CITY DESC ,id ASC
	;
-- 정렬별로 다양하게 오름 혹 내림차순 적용 가능
-- order by
-- 1. 데이터 가져올 때 지정된 컬럼을 기준으로 정렬
-- 2. order by 컬럼명 asc/desc
-- 3. 2개 이상의 정렬기준 , 를 기준으로 각각 지정 가능하나 이경우 먼저 지정한 컬럼 우선
-- 4. 2개 이상의 정렬 기준을 지정할 때 각각 지정 가능하나 이 경우 각 컬럼당 명시적으로 ASC 혹은 DESC 지정해야함
	-- 지정 안할 경우 default로 ASC 적용

-- 연습 문제
-- 1 products 에서 price가 비싼 순으로 모든컬럼 출력
SELECT *
	FROM PRODUCTS
	ORDER BY price DESC 
	;
-- 2 orders에서 order_date 기준으로 최신순으로 출력
SELECT *
	FROM ORDERS
	ORDER BY order_date DESC ;
-- 3 orderdetails 에서 product_id 기준 내림차순 같은 제품 아이디 네에서는 quantity 기준 오름차순 출력
SELECT *
	FROM ORDERDETAILS
	ORDER BY PRODUCT_ID DESC , quantity ASC; -- ASC default이기에 적어도 그만 안적어도 그만
	
-- 여태까지 배운것 기준으로 실무에서 사용하는것 
	-- 실무 에서는 select where order by 를 사용 데이터 추출
	
-- 배달서비스
	-- 1) 2023-08-01 에 주문한 내역 중 쿠폰 할인이 적용된 내용 추출
SELECT *
	FROM 주문정보
	WHERE 주문일자 = "2023-08-01"
		AND 쿠폰할인금액 > 0 
			;

	-- 2) 마포구에서 1인분 배달이 가능한 음식점 추출
SELECT *
	FROM 음식점정보
	WHERE 지역 = "마포구"
		AND 1인분 가능여부 = 1; -- 1을 사용 경우 TRUE,0 은 FALSE
		

	
	
	