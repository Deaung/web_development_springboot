package me.kimdeaung.springbootdeveloper;
/*
    기능구현을 했는데 적용되지 않을 경우

    1. 서버를 재부팅
    2. ctrl + s눌러 세이브 후
    3. intelliJ 를 껏다가 켜기
    4. 서버 재실행
    5. 완료될 가능성 높음.

 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "Hello World!";
    }
}
/*

 */