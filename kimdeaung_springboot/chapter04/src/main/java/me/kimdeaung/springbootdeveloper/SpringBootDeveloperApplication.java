package me.kimdeaung.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
    1. 테스트 코드 개념
        테스트 코드는 작성한 코드가 의도대로 잘 작동하고 예상치 못한 문제가 없는지 확인하는 목적으로
            작성하는 코드

        장점:
            1) 유지 보수 편리
            2) 코드 수정 시 기존 기능이 제대로 작동하지 않을까봐 걱정할 필요 없음

        테스트 코드란?
            테스트 코드는 test 디렉토리에서 작업 - 프로젝트 생성시 src의 하위 폴더
                main  과 같은 라인에 test 폴더가 있음
            테스트 코드에는 다양한 패턴이 있는데 given-when-then 패턴을 사용
                1. given : 테스트 실행을 준비하는 단계
                2. when : 테스트 진행하는 단계
                3. then : 테스트 결과를 검증하는 단계

            ex) 새로운 메뉴를 저장한느 코드를 테스트 한다고 가정했을 때의
            테스트 코드를 given, when, then 으로 구분해서 예시

            @Display
            @Test
            public void saveMenuTest{
                //1.given : 메뉴를 준비 하기위한 과정
                final String name : "아메리카노";
                final int price : 2000;
                final Menu americano = new Menu(name, price);

                2. when : 실제로 메뉴를 저장
                final long saveId = menuService.save(americano)

                3. then : 메뉴 잘 추가 되어있는지 검토
                final Menu saveMenu = menuService.findById(saveId).get();
                assertThat(saveMenu.getName()).isEqual(name);
                assertThat(saveMenu.getPrice()).isEqual(price);
                }
            이상의 코드를 확인하면 세부분으로 나누어짐
            메뉴 저장 과정인 given
            실제 저장하는 when
            저장된걸 확인하는 then

            스프링 부트 3 와 테스트
                스프링부트는 어플리케이션을 테스트하기 위한 도구와 에너테이션 제공
                spring-boot-starter-test 스타터에 테스트 위한 도구 있음

                스프링 부트 스타터 테스트 목록
                    JUnit : 자바 프로그래밍 언어용 단위 테스트 프레임 워크
                    AssertJ : 검증문인 어써션을 작성하는데 사용하는 라이브러리

                    JUnit
                        자바 언어를 위한 단위 테스트 프레임 워크
                            단위 테스트 작성 코드가 의도대로 작동하는지 작은 단위로 검증함을 의미
                            보통의 단위는 메서드
                    JUnit 을 사용하면 단위 테스트결과 객관적으로 나옴

                    특징
                     1.테스트 방식 구분 할 수 있는 에너테이션 제공
                     2. @Test 에너테이션으로 메서드 호출시 마다 새 인스턴스를 생성
                        독립적 테스트 가능
                     3. 예상결과 검증하는 assertion 메서드 제공
                     4. 사용방법이 단순 테스트 코드 작성시간이 적음
                     5. 자동실행 자체 결과 확인 및 실패 지점 보여줌

    test/java 폴더에 JUnitTest.java 파일 생성

    AssertJ 로 검증문 가독성 높이기

        AssertJ 는 JUnit 과 함께 상요해 검증문의 가독성을 높여주는 라이브러리
        예를 들어 방금 작성한 (JUnitTest.java 에서)
        Assertions.assertEquals(sum, a+b); 를 보면 기댓값과 비교값의 구분이 어렵다

        AssertJ 를 사용한 코드라인
        assertThat(a+b).isEqualTo(sum);
        이 경우 a와 b 를 더한 값이 sum 과 같아야한다는 의미로 읽힘

    자주 사용하는 AssertJ 목록

        1. isEqualTo(a) : a와 같은지 검증
        2. isNotEqualTo(a) : a 와 다른지 검증
        3. contains(a) : a를 포함하는지 검증
        4. doseNotContain(a) : a를 포함하지 않는지 검증
        5. starsWith(a) : 접두사가 a인지 검증
        6. endsWith(a) : 접미사가 a인지 검증
        7. isEmpty() : 비어있는 값인지 검증
        8. isNotEmpty : 비어있지 않는 값인지 검증
        9. isPositive() : 양수인지 검증
        10. isNegative() : 음수인지 검증
        11. isGreaterThan(1) : 1보다 큰 값인지 검증
        12. isLessThan(1) : 1보다 작은값인지 검증
        13. isNotNull() : null 이 아닌지 검증
        14. isNull() : null 인지 검증

        테스트 코드 작성 연습문제 - test/java 폴더에 JUnitQuiz

 */
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}