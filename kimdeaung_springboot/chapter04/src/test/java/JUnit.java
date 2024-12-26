import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnit {
    @DisplayName("1+2는 3이다")
    @Test
    public void junitTest(){
        //given
        int a = 1;
        int b = 2;

        int sum = 3;

        Assertions.assertEquals(sum, a+b);
    }
}
/*
    @DisplayName 에너테이션 : 테스트 이름을 명시하는 에너테이션
    @Test 애너테이션을 붙인 메서드는 테스트를 수행 하기 위한 메서드가 됨
        JUnit 은 테스트 끼리 영향을 주지 않도록 각 테스트를 실행 할때마다
        테스트를 위한 객체를 만들고 테스트 조료 시점에 실행 객체를 ㅅ소멸시킴

    .assertEquals() 메서드 : 첫 번째 인수에는 기대하는 값
    두번째 인수에는 실제로 검증할 값을 argument 로 받습니다

    test/java 에 JUnitCycleTest.java
 */