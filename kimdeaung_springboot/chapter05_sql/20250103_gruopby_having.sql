-- users 에서 country별 회원수 구하기 쿼리
SELECT country, COUNT(DISTINCT id) AS uniqueUserCnt -- UNIQUE = DISTINCT 적용
	FROM USERS
	GROUP BY COUNTRY;
-- ---------> 뒤에 나오는 컬럼명 기준으로 그룹화 해서
-- country를 표시하고 count 함수를 적용한 컬럼도 표시해서 보여달라=

SELECT * FROM users;

-- users에서 country가 korea 인 회원중에서 마케팅 수신에 동의한 회원수를 구해 출력
-- 표시 컬럼은 uniqueUserCnt 컬럼 구성
SELECT COUNT(DISTINCT id) AS IS_MARKETING_AGREE
	FROM users
	WHERE COUNTRY = "korea" AND IS_MARKETING_AGREE = 1
	;

-- users에서 country 별로 마케팅수신 동의한 회원수와 그렇지 않은 회원수 구해 출력
-- 표시 컬럼은 country와 uniqueUserCnt 두 컬럼 구성
SELECT country, IS_MARKETING_AGREE , COUNT(DISTINCT id) AS uniqueUserCnt
	FROM USERS
	GROUP BY COUNTRY, IS_MARKETING_AGREE
	ORDER BY country, uniqueUserCnt DESC ;
-- 국가별로 오름차순 uniqueUserCnt 기준으로 내림차순 정렬
-- select절 - from 테이블명 - group by절 - order by절 순서

GROUP BY에 두개 이상의 컬럼을 추가하면 데이터가 여러 그룹으로 나뉨
아르헨티나 기준으로 했을때 마케팅 수신동의 여부가 0 인 회원수와 마케팅 수신여부가 1인 회원수를
기준으로 나뉘어져 있음을 알 수 있음.

예를 들어 위 쿼리문에 경우 country를 기준으로 먼저 그룹화 가 이루워지고 그 후 
is_marketing_agree를 기준으로 그룹화 됨

GROUP BY 에 여러 기준을 설명하면 컬럼 순서에 따라 결과가 달라짐
중요한 기준을 앞에 배치해서 시각화와 그룹화에 대ㅐ한 우선순위 결정할 필요 있

-- group by 정리
-- 1 주어진 칼럼을 기준으로 데이터를 그룹화 하여 '집계함수'와 함께 사용
-- 2 group by 뒤에는 그룹화 할 칼럼명을 입력 그룹화 한 컬럼에 집계함수를 적용하여
		-- 그룹별 계산 수행
-- 3 형식 : group by 컬럼명1,2...
-- 4 group by 에서 두개 이상의 기준의 컬럼을 조건으로 추가하여 여러 그룹으로 분할 가능
	-- 컬럼 순서에 따라 결과 영향 있으므로 우선순위 정해야함

-- users 에서 국가country 내도시city 별 회원수를 구하여 출력
-- 국가명은 알파벳 순서 정렬 같은 국가 내에서는 회원수 기준 내림차순 정렬
-- 표시 칼럼은 country city uniqueUserCnt (where 없음)
SELECT country, city, COUNT(DISTINCT id) AS uniqueUserCnt 
	FROM users 
	GROUP BY COUNTRY , CITY 
	ORDER BY country ASC, uniqueUserCnt DESC;
	
-- substr(컬럼명, 시작값, 글자개수)
-- users에서 월별(e.g 2013-10) 가입 회원 수를 출력
-- 가입 일시컬럼 활용 최신순 정렬
SELECT SUBSTR(CREATED_AT,1,7)AS month,COUNT(DISTINCT id) uniqueUserCnt
	FROM users
	GROUP BY SUBSTR(CREATED_AT,1,7) 
	ORDER BY month desc
	;
SELECT * FROM ORDERDETAILS O ;

	
-- orderdetails 에서 order_id별 주문수량 quantitiy의 총합을출력 
	-- 주문수량의 총 합이 내림차순 정렬
SELECT ORDER_ID ,SUM(QUANTITY)  
	FROM ORDERDETAILs
	GROUP BY ORDER_ID 
	ORDER BY SUM(QUANTITY) DESC ;

-- orders 에서 staff_id 별 user_id 별로 주문 건수를 출력
	-- 단 staff_id 기준 오름차순 주문건수별 기준 내림차순
SELECT * FROM ORDERS;
SELECT STAFF_ID ,USER_ID, COUNT(*) 
	FROM orders
	GROUP BY STAFF_ID ,USER_ID 
	ORDER BY STAFF_ID ASC, COUNT(*) DESC
	;
	
-- orders 에서 월별로 주문한 회원수를 출력(orer_date 이용 최신순 정렬)
SELECT SUBSTR(ORDER_DATE,1,7) , COUNT(DISTINCT USER_ID) 
	FROM orders
	GROUP BY SUBSTR(ORDER_DATE,1,7)
	ORDER BY SUBSTR(ORDER_DATE,1,7)DESC \
	

-- having
	-- group by를 이용해서 데이터를 그룹화 하고 해당 그룹별로 집계 연산을 수행하여
	-- 각 국가별 회원수 도출 가능해 짐
	-- 예를 들어 회원수가 n명 이상인 국가의 회원수만 보는 등의 조건을 걸어보자
	
-- where절 이용하는 방법도 있으나 추가적 개념에 대해 학습할 예정
	-- where절이 언제나 용이하지 않다는 것을 알아야 함
	
-- users에서 country가 korea usa france 인 회원 숫자 도출
SELECT country , COUNT(DISTINCT id)
	FROM users 
	WHERE country IN ("korea","usa","france")
	GROUP BY COUNTRY ;

WHERE을 통해 원하는 국가만 필터링하고 GROUP BY 를 사용
여기서 필터링 조건은 country 컬럼의 데이터 값에 해당

만약 회원수가 8명 이상인 국가 회원수만 필터링하려면?
-- users에서 회원수가 8명 이상인 country 별 회원수 출력

SELECT country, COUNT(DISTINCT id)
	FROM users
	GROUP BY COUNTRY 
	HAVING COUNT(DISTINCT id) >= 8
	ORDER BY 2 DESC;

1. WHERE 에서 필터링 시도 할 때ㅔ 오류가 발생하는 이유
	where은 GROUP BY 보다 먼저 실행 되기 때문에 그룹화를 진행하기 전에
	필터링 함, 하지만 집계함수로 계산된 값의 경우 그룹화 이후에 이루어지기 때문에
	순서상으로 GROUP BY 보다 뒤에 있어야 해서 WHERE 절 도입이 불가능 함
	
2. 즉 GROUP BY 를 사용한 집계 값을 필터링 할 때는 HAVING 을 사용

-- orders에서 staff_id 별 주문건수와 주문 회원 수를 계산하고 주문 건수가 10건 이상이면서 
-- 주문 회원수가 40명 이하인 데이터만 출력(단 주문건수 기준 내림차순정렬 )

SELECT staff_id, COUNT(DISTINCT id), COUNT(DISTINCT USER_ID) 
	FROM orders
	GROUP BY STAFF_ID 
	HAVING COUNT(DISTINCT id)>= 10 AND COUNT(DISTINCT USER_ID)<=40
	ORDER BY COUNT(DISTINCT id)DESC 
	;

-- HAVING 정리
-- 순서상 GROUP BY 뒤쪽에 위치하며 GROUP BY 와 집계함수로 그룹별로 집계한 값을 필터링시 사용
-- 
-- WHERE 과 동일하게 필터링 역할 수행하지만 적용 영역의 차이 때문에 주의 필요있음
-- WHERE 은 FROM 에서 불러온 데이터를 직접 필터링하는 반면
-- HAVING 은 GROUP BY 가 실행 된 이후 집계함수 값을 필터링함
-- 따라서 HAVING 은 GROUP BY 가 SELECT 문 내에 없다면 사용 불가능
-- 
-- SELECT 문 실행 순서
-- SELECT 컬럼명			- 5
-- 	FROM 테이블 명			- 1
-- 	WHERE 조건1			- 2
-- 	GROUP BY 컬럼명		- 3
-- 	HAVING 조건2			- 4
-- 	ORDER BY 컬럼명		- 6

-- 1.컴퓨터는 가장 먼저 FROM 을 읽어 테이터가 저장된 위치에 접근하고 테이블 존재 유무를 따진 후
-- 테이블 확인하는 작업 실행
-- 2.WHERE 을 실행하여 가져올 데이터의 범위 확인
-- 3.데이터베이스에서 가져오기 범위가 결정된 데이터에 한해서 연산을 적용할 수 있게 그룹으로 데이터 나눔
-- 4.HAVING 은 바로 그 다음 실행되며 이미 GROUP BY 를 통해 연산 적용이 가능해진 상태이기에 
-- 	집계 연산 실행
-- 5.이후 SELECT 를 통해 특정 컬럼 혹은 집계함수 적용 컬럼을 조회하여 값을 보여줌
-- 6.ORDER BY 를 통해 특정 컬럼 및 연산 결과를 통한 오름차순 및 내림차순으로 나열

SELECT * FROM ORDERS O ;
-- 연습 문제
-- 1. orders에서 회원별 주문건수 추출(단 주문건수가 7건 이상인 회원 정보만 추출, 주문건수 기준 내림차순,user_id와 주문아이디(id)컬럼 활용
SELECT user_id, COUNT(DISTINCT id) 
	FROM ORDERS
	GROUP BY user_id 
	HAVING COUNT(DISTINCT id) >= 7
	ORDER BY COUNT(DISTINCT id) DESC 
	;
-- 2. users에서 country별 city 수와 국가별 회원(id) 수를 추출
-- 	단, 도시 수가 5개 이상이고 회원수가 3명 이상인 정보만 추출
-- 	도시 수 회원 수 기준으로 모두 내림차순
SELECT country, COUNT(DISTINCT id) , COUNT(DISTINCT city) 
	FROM users 
	GROUP BY country 
	HAVING COUNT(DISTINCT city)>=5 AND COUNT(DISTINCT id)>=3
	ORDER BY COUNT(DISTINCT id) DESC , COUNT(DISTINCT city) DESC 
	;
-- 3.users 에서 usa,brazil,korea,argentina,mexico 에 거주중인 회원수를 
-- 	국가별로 추출(단 회원 수가 5명 이상인 국가만 추출, 회원수 기준 내림차순)
SELECT country, COUNT(DISTINCT id) 
	FROM users 
	WHERE country IN("korea","usa","brazil","argentina","mexico")
	GROUP BY country
	HAVING COUNT(DISTINCT id) >= 5
	ORDER BY COUNT(DISTINCT id) DESC 
	;

-- sql 실무 상황에서의 group by & having
-- 1. 2025-01-03 에 음식 분류 별 주문개수 집계
-- SELECT 음식분류, COUNT(DISTINCT 주문아이디) AS 주문건수
-- 	FROM 주문정보
-- 	WHERE 주문시간(월)- "2025-01"
-- 	GROUP BY 음식분류
-- 	ORDER BY 주문건수 DESC ;
	