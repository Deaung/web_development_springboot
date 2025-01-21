package me.ahngeunsu;

import java.util.Comparator;
import java.util.function.Function;

/*
    1. 람다식 (Lamda Expression)의 정의
        : java8 에서 도입된 프로그래밍 기능
        함수형 프로그래밍을 java에 도입하기 위한 핵심기능

        익명함수를 생성하는 표현식, 코드의 간결성 및 가독성을 높이는에 유용

        형식:
        (매개변수) -> {실행문}

        매개변수 : 람다식에서 처리할 입력 값
        화살표(->) : 매개변수와 실행코드 구분
        실행문 : 람다식이 수행할 작업

        ex : 숫자를 두배로 만드는 함수 람다식 이용 다음과 같이 작성
        x -> x * 2

        2. 람다식의 특징
            1) 간결성 : 익명 클래스 구현이나 기존 메서드의 정의 방식보다 짧고 간단함
                -> 클래스 나눠서 일일이 method 구분할 필요 없음
            2) 함수형 인터페이스 : 함수형 인터페이스란 단 하나의 추상메서드만 가지는 인터페이스로
                대표적으로 Runnable, Callable, Comparator 등 있음
            3) 지연실행 : 람다식은 실행될 때 까지 평가되지 않음, 코드의 지연 실행 가능
            4) 컨텍스트 의존성 : 람다식의 타입은 함수형 인터페이스를 구현하는 곳에 따라 결정

        3. 기존 메서드 표기법과의 비교 및 대조
            main 확인할 것

        4. 람다식의 장단점
            장점 :
                간결성
                효율성 : 불필요한 익명 클래스 생성 줄어 메모리 사용 최적화
                함수형 프로그래밍 지원 : java 에서 객체지향에서 벗어나 함수형 프로그래밍을 구현할 수 있는 도구 제공
                코드 유지 보수성 : 간단한 동작을 위한 코드는 수정 및 유지 보수 편함

            단점:
                복잡한 로직 구현의 어려움 : 긴 메서드를 구현 하는 경우 비 효율적
                디버깅 어려움 : 익명 클래스에 비해 디버깅이 어려움,
                    -> 오류 상황에서 람다식 위치 파악 어려움
                함수형 인터페이스 제한 : 함수형 인터페이스를 기반으로 동작하기에 모든 경우에 사용가능한 것이 아님
 */
public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };

        Comparator<Integer> comparator1 = (((o1, o2) -> o1 - o2));

        //숫자 제곱하는 람다식
        Function<Integer, Integer> square = x-> x * x;

        System.out.println("4의 제곱: "+ square.apply(4));
        System.out.println("5의 제곱: "+ square.apply(5));

        // ()->{실행문} : 매개변수 없는 경우 사용

    }
}