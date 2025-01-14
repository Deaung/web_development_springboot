package me.kimdeaung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kimdeaung.springbootdeveloper.domain.User;
import me.kimdeaung.springbootdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 사용자 이름(email) 으로 사용자 정보 가져오는 메서드 오버라이딩
    @Override
    public User loadUserByUsername(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }

}
/*
    스프링 시큐리티에서 사용자 정보를 가져오는 UserDetailsService 인터페이스 구현
    작성한 클래스 명 : UserDetailService.java
    필수로 구현할 필요 있는 loadUserByUsername 메서드 오버라이딩 후 사용자 정보 가져오는 로직 작성

    시큐리티 설정파일 생성
        springbootdeveloper 패키지 우클릭 새 패키지 config
 */
