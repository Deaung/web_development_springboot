package me.ahngeunsu.springbootdeveloper.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {
    // 요청값(이름 값 만료기간)을 바탕으로 쿠키 추가
    public static void addCookie(HttpServletResponse response,
                            String name,
                            String value,
                            int maxAge){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    //쿠키 이름 입력 받아 삭제
    public static void deleteCookie(HttpServletRequest request,
                                    HttpServletResponse response,
                                    String name){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return;
        }
        for (Cookie cookie : cookies){
            if (name.equals(cookie.getName())){
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    /*
        deleteCookie : 쿠키 이름을 입력받아 쿠키를 삭제
            실제로 삭제하는 방법 없으므로 파라미터로 넘어옴
            빈값으로 바꾸고 만료 시간을 0으로 설정해서 쿠키가 재 생성 되자마자
            만료처리를 함으로써 구성하였음

            2025-01-20 객체 직렬화/ 역직렬화 메서드 구현 예정
     */
}
