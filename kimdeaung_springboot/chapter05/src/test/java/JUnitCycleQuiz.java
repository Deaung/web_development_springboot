import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCycleQuiz {
    /*
        문제 03
        각각의 테스트를 시작하기 전에 Hello 출력하는 메서드와 모든 테스트 마치고
        Bye 출력하는 메서드 추가
     */
    @Test
    public void junitQuiz03(){
        System.out.println("this is the first test.");
    }
    @Test
    public void junitQuiz04(){
        System.out.println("this is the second test.");
    }
    @BeforeEach
    public void junitQuizBefore(){
        System.out.println("Hello");
    }
    @AfterAll
    public static void junitQuizAfter(){
        System.out.println("Bye");
    }
    /*
        여기에서 클래스 전체를 실행하면 콜솔에 다음과 같이 출력되도록 작성

        Hello
        this is the first test
        Hello
        this is the second test
        Bye

        TestController.java
     */
}
