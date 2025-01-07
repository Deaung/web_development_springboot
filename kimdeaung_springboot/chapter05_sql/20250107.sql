-- UNION 
-- 	컬럼 목록이 같은 데이터를 위 아래로 결합
-- 	데이터를 위 아래로 수직 결합시키는 기능 ->> 컬럼의 형식과 개수가 같은 두 데이터 결과 집합을
-- 	하나로 결합

--	JOIN 의 경우에는 여러가지 조건을 미리 충족시켜야 함 -> ON 을 통한

(SELECT * FROM users ) UNION (SELECT * FROM users);

-- 이상의 쿼리 문제점은 결합 기능을 지닌 union의 결과값이 select * from users; 와
--	동일하다는 점

-- 해당 이유는 : union은 결합하는 두 결과 집합에 대한 중복제거 기능이 포함되어있다

-- 중복제거 안하고 출력하는 명령어
(SELECT * FROM users ) UNION ALL (SELECT * FROM users )
ORDER BY id;

-- union은 중복제거 가능/ union all 은 중복 포함 출력

-- 참고 union all 사용시 select 에서 컬럼 선별 예시
	(SELECT id, phone, city, country -- 일부 컬럼만 지정해서 출력
		FROM users)
		UNION ALL
		(SELECT id, phone, city, country -- 기준이 되는 첫번째 select절에서 선택하는 컬럼의 종류 및 개수가 완벽히 일치
		FROM users)
		ORDER BY id;

-- 실무에서는 union all이 권장됨. union 의 경우 중복제거가 있는데 대량의 데이터를 대상으로
-- 중복 제거 할 때 컴퓨터에 무리한 연산 부하를 줄 가능성이 있기때문
-- 일단 UNION ALL 을 통해서 최종결과 형태 확인 후에 UNION을 적용하는 식으로 프로세스 짜여있음

-- users에서 country가 korea 인 회원정보만 추출, mexico 인 회원 정보만 추출해서 결합하기
-- 컬럼은 id phone city country출력 결과 집합은 country 기준 알파벳 순으로 
(SELECT id, phone, city, country
	FROM users 
	WHERE country = "korea")
	UNION ALL 
	(SELECT id,phone,city, country
	FROM users 
	WHERE country = "mexico")
	ORDER BY country;
	
-- 연습문제
-- 1 orders에서 order_date 가 2015.10월 건과 2015.12 월인 건을 select로 각각 추출 후
-- 두 결과 집합을 UNION ALL 을 사용해 하나로 결합 결과 는 최신순
-- 1번쿼리
(SELECT *
	FROM orders 
	WHERE SUBSTR(ORDER_DATE , 1, 7) = "2015-10")
	UNION ALL
	(SELECT *
	FROM orders 
	WHERE SUBSTR(ORDER_DATE , 1, 7) = "2015-12")
	ORDER BY order_date DESC ;
-- 2번 쿼리
(SELECT* FROM orders
	WHERE order_date >= "2015-10-01" AND order_date < "2015-11-01")
	UNION ALL 
	(SELECT* FROM orders 
		WHERE order_date >= "2015-12-01" AND order_date < "2016-01-01")
		ORDER BY order_date;

-- sql 상에서의 문자열 비교 방식
-- 문자열을 왼쪽에서 오른쪽으로 한 문자씩 비교
-- ascii / 유니코드 값을 기준으로 비교한다
-- 왼쪽부터 읽어오다가 다른 문자가 발견되는 순간 그 값에 따라 크고 작음을 판별

-- "2015-10-01" vs "2015-11-01" 의 경우
-- 문자열 순서대로 비교중 0 과 1이 다른 시점에 들어갔을경우 크기의 비교가 이루어짐

-- YYYY-MM-DD 형식으로 지정되어있다면 문자열 비교 결과와 질제 날짜 비교 결과가 동일하게 적용
-- MM-DD-YYYY 형태로 되어있다면 오류 발생 가능성 있다.




-- 2 users 에서 usa 에 거주중이면서 마케팅 수신에 동의한 회원정보와 france에 거주중이면서 
-- 마케팅 수신에 동의하지 않은 회원정보를 SELECT 로 각각 추출 후 두 결과집합을 UNION all을 
-- 사용해 하나로 결합(최종결과는 id phone country city is_marketing_agree
-- 컬럼 추출 후 거주국가 기준 알파벳 역순으로 추출)
(SELECT id, phone, country, city, is_marketing_agree
	FROM users 
	WHERE country = "usa" AND is_marketing_agree = "1")
	UNION ALL 
	(SELECT id, phone, country, city, is_marketing_agree
	FROM users 
	WHERE country = "france" AND is_marketing_agree = "0")
	ORDER BY country DESC 
	;





 
-- 3 UNION을 활용하여 orderdetails와 products 를  FULL OUTER JOIN 조건으로 결합하여 출력
(SELECT * FROM ORDERDETAILS O LEFT JOIN PRODUCTS P ON o.product_id = p.id)
UNION 
(SELECT* FROM ORDERDETAILS O RIGHT JOIN PRODUCTS P ON o.product_id = p.id)
;
-- 굳이 이런 형식으로 사용 안함

-- 서브 쿼리
SQL 쿼리 결과를 테이블 처럼 사용 쿼리 속 쿼리
서브 쿼리는 작성한 쿼쿼리를 소괄호로 감싸서 사용, 실제 테이블은 아니지만
테이블 처럼 사용 가능

-- produts 에서 name과 price 모두 불러오고 
-- 평균 정상 가격으로 새 컬럼 만들어 출력

SELECT name, price, (SELECT AVG(price) FROM products )FROM PRODUCTS ;
-- SELECT AVG(price) FROM products 를 하는 경우 전체 price/ 행의 개수로 나눈 데이터가
-- 단 하나 이므로 SELECT name,price ,AVG(price) FROM products 로 작성하ㅕㅁㄴ
-- 1행 짜리 도출

-- products 테이블에 name/price 를 불러오는 것 기본적인 SELECT 절
-- SELECT 절에는 단일값 반환하는 서브 쿼리 가능
-- 
-- 스칼라 서브쿼리 : 쿼리 결과 단일값 반환 하는 서브쿼리

SELECT name, price, 38.5455 AS avgPrc FROM PRODUCTS;

-- 특정한 단일 결과값을 각 행에 적용하고 싶다면 이상과 같이 하드코딩 가능
-- 정확한 값을 얻기위해서 사전 쿼리문으로
-- SELECT AVG(price) FROM PRODUCTS;가 요구됨으로 효율적 아님
-- 실무에서 실제 전제 쿼리문 실행 후 이후에 확인해야 해서 
-- 서브 쿼리 작성하는 편 권장

-- 스칼라 서브 쿼리 작성시 단일값이 반환되도록 작성해야 한다는 점에 유의
만약 2개 이상의 집계값을 기존 테이브렝 추가하여 출력하고 싶다면 서브쿼리로 따로 나누어서
작성할 필요 있다

-- users에서 city 별 회원수 카운트 후 회원후 3명 이상인 도시 명과 회원수 출력 
-- 회원수 기준 내림차순
SELECT city,COUNT(DISTINCT id) 
	FROM users GROUP BY city
	ORDER BY COUNT(DISTINCT id)DESC; -- 결과값이 도시별로 id 갯수를 계산함
-- 1번 쿼리
SELECT city,COUNT(DISTINCT id) 
	FROM users 
	GROUP BY city
	HAVING COUNT(DISTINCT id) >=3
	ORDER BY COUNT(DISTINCT id)DESC
	;


-- orders 와 staff 를 활용하여 last_name 이  kyle 이나 scoutt 인직원의 담당주문 출력
-- (서브쿼리 활용하자)

SELECT o.id, o.USER_ID ,s.LAST_NAME 
	FROM ORDERS O LEFT JOIN STAFF S ON o.STAFF_ID = s.id
	WHERE s.LAST_NAME = "kyle" OR s.LAST_NAME = "scott"
	ORDER BY s.LAST_NAME ;

SELECT id
	FROM STAFF WHERE LAST_NAME IN ("kyle","scott"); -- 조건절에 쓰일 경우 스칼라 서브가 아님을 주목

-- 이상의 코드는 staff 테이블에서 id 값이 3인 것과 5인것 도출함
-- 해당 결과 가지고 orders 테이블에 적용하는 형태로 작성

SELECT *
	FROM orders 
	WHERE STAFF_ID IN (
	SELECT id
	FROM STAFF WHERE LAST_NAME IN ("kyle","scott")
	)
	ORDER BY STAFF_ID 
	;

-- where 절 내에서 필터링 조건 지정을 위해 중첩된 서브 쿼리를 작성 가능
-- WHERE 에서 IN 연산자와 함께 서브쿼리 활용할 경우 : 
-- 컬럼 개수와 필터링 적용대상 컬럼 개수가 일치해야만 한다
-- 
-- 이상의 코드에서 서크 쿼리의 결과 도출되는 컬럼 숫자 = 1 staff테이블의 id / 행 =2

SELECT *
	FROM orders 
	WHERE (STAFF_ID, USER_ID) IN ( -- 필터링 대상 컬럼 개수 = 2
	SELECT id, USER_ID 				-- 서브쿼리 컬럼개수 2
	FROM STAFF WHERE LAST_NAME IN ("kyle","scott")
	)
	ORDER BY STAFF_ID 
	;

-- 결과 값으로 직원정보 테이블에 존재하는 id user_id 와 동일한 값이 orders 테이블의
-- staff_id user_id 컬럼에 있을경우 반환하여 출력합니다
-- 이상의 쿼리문의 해석 : 직원 자신이 자기 쇼핑몰에서 주문한 이력이 반환된 것

-- products 에서 discount_price가 가장 비싼 제품 정보 출력
-- 단, products 의 전체 컬럼이 출력되도록한다
SELECT MAX(discount_price) FROM PRODUCTS; 

SELECT *
FROM PRODUCTS 
WHERE DISCOUNT_PRICE in(
	SELECT MAX(discount_price) FROM PRODUCTS
	);


-- orders 에서 주문 월(order_date 컬럼 활용)이 2015-7인 주문정보를
-- 주문상세 정보 테이블 orderdetails 에서 quantity가 50 이상인 정보를 각각 서브 쿼리로 작성하고 
-- INNER JOIN 하여 출력하자
-- 1)
SELECT *
	FROM orders 
	WHERE ORDER_DATE >= "2015-07-01" 
		AND order_date <"2015-08-01";

-- 2)
SELECT *
	FROM ORDERDETAILS
	WHERE QUANTITY >= "50";

-- 1과 2번 inner join은 from 절에서
SELECT *
	FROM (SELECT *
			FROM orders 
			WHERE ORDER_DATE >= "2015-07-01" 
				AND order_date <"2015-08-01") o 
				INNER JOIN 
				(SELECT *
					FROM ORDERDETAILS
					WHERE QUANTITY >= "50")	od 
					ON o.id = od.order_id
	;

-- 서브 쿼리 작성 위한 방안중 하나는 서브쿼리에 들어가게 될 쿼리문을 작성한 결과값을 확인
-- 이후 해당 쿼리가 스칼라인지 아닌지 따라 그 위치 역시 통제 가능
-- 스칼라는 select 절에 사용 
-- 방금전 쿼리문의 경우 결과값이 테이블형태로 나왔기에 이를 기준으로 inner join

-- 서브쿼리 정리
-- 쿼리 결과값을 메인 쿼리에서 값이나 조건으로 사용하고 싶을 때 사용

-- select from where 등 사용 위치에 따라 불리는 이름이 다르다

-- select 절에서의 사용
-- 형태
-- select ...,([서브쿼리])as [컬럼명]
-- ... 이하 생략

-- select 에서는 단일 집계값 을 신규 컬럼으로 추가하기위해 서브쿼리 사용
-- 여러개의 컬럼을 추가하고 싶을 때는 서브쿼리 여러개 작성하면 됨
-- 특징 : select 의 서브쿼리는 메인 쿼리의 from 에서 사용된 테이블이 아닌 테이블도 사용 가능
-- 때문에 불필요한 조인 수행을 줄일 수 있는 장점 있다

-- 2. from 에서의 사용
-- 형태
-- select ...
-- from ([서브쿼리])a
-- ...

-- from 에서 사용되는 서브쿼리 : 인라인 뷰, 마치 테이블처럼 서브쿼리의 결과값을 사용가능.
-- 또한 from 에서 2개 이상의 서브 쿼리를 활용하여 join 연산 가능
-- 이때 join 연산을 위해 별칭 생성이 가능한데 서브 쿼리가 끝나는 괄호 뒤에 공백 한칸 주고 
-- 별칭 지정 가능

-- 특징:
-- from 에서 서브쿼리 적절히 활용하면 적은 연산으로 같은 결과를 도출 가능, 단 rdbs 기준
-- 테이블 검색을 빠르게 할 수 있는 인덱스* 개념이 있는데 서브쿼리를 활용하면 인덱스를 사용하지 못하는 
-- 경우가 있으므로 주의

-- 정리 3 WHERE 에서의 사용
-- 
-- 형태:
-- ...
-- WHERE [컬럼명][조건연산자]([서브쿼리])
-- ...
-- 
-- WHERE 에서의 필터링을 위한 조건 값을 ㅅ설정하는데 서브쿼리 사용 가능
-- 위의 예시에서는 IN 연산자 사용했으나 다른 연산자 사용 또한 가능
-- 
-- 특징 : IN 연산자의 경우 다중컬럼 리뷰를 할때는 서브쿼리에서 추출하는 컬럼의 갯수와
-- where에 작성하는 필터링 대상 컬럼 개수가 일치해야함 - > 이때 필터링 대상 컬럼이 2개 이상이면
-- () 로 묶어서 작성할 필요가있다.

-- 1데이터 그룹화 하기(group by + 집계함수)
-- 2 데이터 결과 집합 결합하기 (join + 서브쿼리)
-- 3 데이터 결합 후 그룹화 하기(join+group by)
-- 4 서브쿼리로 필터링 (where+서브쿼리)
-- 5 같은 행동 반복 대상 추출(left join)

-- 1. users 에서 created_at 컬럼 활용하여 연도별 가입 회원수 추출

SELECT SUBSTR(CREATED_AT, 1, 4), COUNT(DISTINCT id) 
	FROM users
	WHERE CREATED_AT IS NOT NULL 
	GROUP BY SUBSTR(CREATED_AT, 1, 4)
	;

-- 2. users 에서 country, city , is_auth 컬럼 활용 국가별 도시별로 본인인증한 회원수 추출
SELECT COUNTRY ,city ,sum(is_auth) -- is_auth 가 1이면 본인인증 한거니까 is_auth들의 합이 
	FROM users
	GROUP BY country, city
	ORDER BY sum(is_auth) DESC;
		







-- index 테이블의 검색속도를 높이는 기능으로 컬럼값을 정렬하여 검색시 더욱 빠르게 찾아내도록 하는 자료구조 








