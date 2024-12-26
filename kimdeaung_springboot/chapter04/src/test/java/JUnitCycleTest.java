import org.junit.jupiter.api.*;
/*
    * 의미는 SQL 문에서 배우겠지만
     all의 뜻을 지님
     org.junit.jupiter.api 이하의 모든 것 import
 */

public class JUnitCycleTest {

    @BeforeAll//
    static void beforeAll(){
        System.out.println("@BeforeAll");
    }
    @BeforeEach
    public void beforeEach(){
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1(){
        System.out.println("@test1");
    }
    @Test
    public void test2(){
        System.out.println("@test2");
    }
    @Test
    public void test3(){
        System.out.println("@test3");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("@AfterAll");
    }
    @AfterEach
    public void afterEach(){
        System.out.println("@AfterEach");
    }
}
/*
    @beforeAll
        전체 테스트를 시작하기 전에 처음 한번만 실행
        데이터베이스에 연결해야 하거나 테스트 환경 초기화시 사용
        실행주기에서 한번만 호출되어야 ㅎ하기때문에 메서드 static 으로 선언

    @beforeEach
        테스트 케이스 시작하기 전 매번 실행
        테스트 메서드에서 사용하는 객체를 초기와하거나 테스트레 필요한 값을 미리 넣을때 사용
        각 인스턴스에 대해 메서드를 호출해야함 메서드는 static 아님

    @AfterAll
            전체 테스트 마치고 종료전 한번 실행
            데이터베이스 연결 종료할때나 공통적으로 사용하는 자원 해체시 사용
            메서드는 static 선언

    @AfterEach
        테스트 케이스 종료 전사용
        각 테스트 이후 특정 데이터 삭제해야하는 경우 사용

 */
