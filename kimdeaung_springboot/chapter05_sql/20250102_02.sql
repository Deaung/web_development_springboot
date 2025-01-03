-- 데이터를 그룹화 하고 함수로 계산하는 방법
-- 그룹화는 조건에 따라 데이터를 그룹으로 묶는것(where과 차이가 있음)
-- 데이터를 그룹화 하면 함수로 원하는 계산 가능

-- 예를 들어 실습 데이터에서 회원수를 구하려면, 전체 회원수가 아닌 국가별 회원수를 구하는 방법
-- 제품별 매출 구하는 방법 등 실제로 알아 볼 수 있음

-- sql 내에서 사용하는 함수는 평균 갯수 합계 등 구하는 집계 함수
-- 문자열을 원하는 만큼 잘라내거나 대 소문자 변경하는 기능을 수행하는 일반 함수

-- 함수 적용 위한 전제 조건 : group by
-- 전체 데이터를 째로 함수 적용하는 경우도 있으나 그룹별로 수치를 도출하는일도 있는데
-- 이 경우 group by
-- 이를 이용 데이터를 그룹으로 묶은 후 필요 함수 적용 가능
-- 예를 들어 국가별 회원수 구하라 혹은 일별 매출 계산하라 와 같은
-- 그룹화 기준을 지정해서 원하는 계산 수행 가능

-- gruop by 계산한 결과를 필터링 하는 having 명령어 있음
-- gruop by 적용해서 국가별 회원수 구한 후 회원수 10명 이상인 국가만 도출하라 
-- 집계 함수로 계산한 결과 중 조건에 맞는 데이터만 필요한 경우
-- where 을 쓰는것이 아닌 having 을 사용

-- 집계 함수 예
-- 예제 : users 에서 모든 행 수 세어본다 : count
SELECT *
	FROM users
	;
SELECT COUNT(*)		-- count(컬럼명) 가 집계함수
	FROM users 		-- 대상 테이블의 행 갯수 세는 역할
	;

-- users 에서 존재하는 country 컬럼의 데이터 갯수
SELECT COUNT(country) -- country 컬럼에서 NULL 이 아닌 갯수 중복으로 계산
	FROM users 
	;
SELECT * 
	FROM USERS
	WHERE COUNTRY IS NULL
	;

-- country 데이터 값의 종류 계산
SELECT COUNT(DISTINCT country) -- 중복을 제거하기 위해서 DISTINCT 사용
	FROM USERS
	;
-- distinct : 중복값을 제거하여 고유한 값을 반환하는 키워드 / select 와 함께 사용

SELECT COUNT(1) -- count(칼럼숫자) 가능
	FROM USERS
	;

-- min / max / count / sum / avg

-- products 에서 최저가를 구하시오
SELECT MIN(price)
	FROM PRODUCTS
	;
-- products 에서 최대가를 구하시오
SELECT MAX(price)
	FROM PRODUCTS
	;
-- products 에서 전체 데이터 가격의 합 구하시오
SELECT SUM(price)
	FROM PRODUCTS
	;
-- products 에서 price 의 평균(단, 결과값을 컬럼명 avgPrice 로 변경)
SELECT ROUND(AVG(price),2) AS "avgPrice"
	FROM PRODUCTS
	;
-- round (집계함수의 결과, 소수점 몇째자리 까지인지)
sum : 합계 : sum (컬럼명)
avg : 평균 : avg (컬럼명)
min : 최솟값 : min (컬럼명)
max : 최대값 : max (컬럼명)
count : 갯수 : count (컬럼명)

round (칼럼명, 소수점자리수) : 소수점자리를 지정한 자리수 까지 반올림 하여 반환
substr(칼럼명,시작위치,가져올 문자갯수) : 문자열 지정한 시작점부터 지정한 문자 갯수 만큼 
LENGTH(칼럼명 ) : 문자열 길이 반환
UPPER(칼럼명) : 알파벳 문자열 대문자 반환
LOWER(칼럼명): 알파벳 문자열 소문자 반환

집계함수는 여러 행의 데이터를 하나의 결과값으로 집계/ 일반함수는 한 행의 데이터에 하나의 결과값 반환
이상을 이유로 집계함수는 SELECT에서만 사용 가능
일반함수는 SELECT뿐만 아닌 WHERE에서도 사용 가능
이상의 함수는 전부가 아닌 일부이므로 필요시마다 소개예정

GROUP BY
	-- 어떤 기준으로 묶어서 계산하느냐와 관련있는 키워드
	-- 집계함수만으로 원하는 결과 얻을 수 없을 때(데이터 전체가 아닌 원하는 기준으로
	-- 그룹을 나눈 상테에서 계산을 해야 할때) 의미가 있다
	-- 예를 들어 전체 회원수가 아닌 국가별 회원수를 나누어 계산하기도 하며
	-- 월별로 가입한 회원수를 집계하기도 하기 때문
	-- 특정 조건으로 나눈 그룹 별 계산을 수행 할때는 먼저 그룹화 선행해야 함
	-- 그 후 집계함수는 특정 컬럼을 기준으로 데이터를 그룹화 한 후에 그룹별 집계함수 적용

-- users 에서 country 가 korea 인 회원수

SELECT COUNT(id)
	FROM USERS
	WHERE COUNTRY = "korea";
	-- 이상의 경우 country = korea 인 경우는 출력 가능하나
SELECT COUNT(DISTINCT country)
	FROM USERS
	;
-- 를 확인하면 count (distinct country) 의 결과값이 17이므로 모든 국가를 대상으로 회원수 확인하기 위해서는 17번의 sql문 작성 필요
-- 상당히 비 효율적이기에 

-- 다음 시간에 group by 를 통해 어케 묶을지 학습할 예정