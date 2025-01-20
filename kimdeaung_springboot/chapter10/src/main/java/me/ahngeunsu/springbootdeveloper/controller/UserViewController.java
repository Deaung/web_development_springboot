package me.ahngeunsu.springbootdeveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
//    @GetMapping("/login")// 기존 로그인
//    public String login() {
//        return "Login";
//    }
@GetMapping("/login")
public String login() {
    return "oauthLogin";
}
// oauth 관련 로그인 페이지

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

}
