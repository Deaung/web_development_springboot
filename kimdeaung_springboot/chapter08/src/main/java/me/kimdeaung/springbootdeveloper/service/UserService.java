package me.kimdeaung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kimdeaung.springbootdeveloper.domain.User;
import me.kimdeaung.springbootdeveloper.dto.AddUserRequest;
import me.kimdeaung.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
    /*
        1. 패스워드 암호화 - 패스워드 저장시 시큐리티를 설정하며 패스워드 인코딩용으로 등록한 빈을 사용해서
            암호화한 이후 저장

            컨트롤러 작성
                회원가입 폼에서 회원가입 요청받으면 서비스 메서드 사용
                사용자 저장한 뒤 로그인 페이지로 이동하는 signup() 메서드 사용 예정

                controller 패키지에 UserApiController 파일 생성
     */
}
