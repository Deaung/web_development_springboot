package me.ahngeunsu.springbootdeveloper.config.oauth;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.User;
import me.ahngeunsu.springbootdeveloper.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override // alt+insert override 메서드
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        //오류
        saveOrUpdate(user);

        return user;
    }
    // 위 빨간색 되어있는 메서드 정의
    private User saveOrUpdate(OAuth2User oAuth2User){
        Map<String , Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        User user = userRepository.findByEmail(email)
                .map(entity ->entity.update(name))
                .orElse(User.builder()
                        .email(email)
                        .nickname(name)
                        .build()); // User 정보 없으면 새로 저장 --빌더 빌드 패턴

        return userRepository.save(user);
    }

}
/*
    부모 클래스인 DefaultOAuth2UserService 에서 제공하는 OAuth 서비스에서 제공하는 정보를 기반으로
    유저 객체를 만들어 주는 loadUser() 메서드 사용-- 객체 불러옴(Override 단축키 사용)

    구글 기준으로 사용자 객체 내부는
        식별자
        이름
        이메일
        프로필 사진 링크 등 정보 포함

    saveOrUpdate() 메서드 통해 사용자가 users 테이블에 존재하면 업데이트
    없으면 저장(DB)

    OAuth2 설정 파일 작성 예정
        -> OAuth2 사용은 거의 기본적으로 JWT 와 연동
        기존 스프링 시큐리티 구현하면서 작성한 설정이 아닌 추가적인 작업 필요

        -> 기존의 폼 로그인 방식을 구현하려고 사용한 설정 갈아버릴예정
        WebSecurity
 */