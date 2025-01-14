package me.kimdeaung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.kimdeaung.springbootdeveloper.dto.AddUserRequest;
import me.kimdeaung.springbootdeveloper.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request); // 회원가입 메서드 호출
        return "redirect:/login";
    }
    /*
        회원가입 절차가 완료된 이후 로그인 페이지로 이동하기 위한 "redirect:" 사용
        회원가입 절차 끝났을 때 강제로 /login url 에 해당하는 화면으로 이동

        회원가입 로그인 뷰 작성

            뷰 관련 컨트롤러 구현

     */
}
