package me.ahngeunsu.springbootdeveloper.config.jwt;

import io.jsonwebtoken.Jwts;
import me.ahngeunsu.springbootdeveloper.domain.User;
import me.ahngeunsu.springbootdeveloper.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  JwtProperties jwtProperties;

    //1. generateToken
    @DisplayName("generateToken() : 유저 정보와 만료 기간을 전달해 토큰 생성 가능")
    @Test
    void generateToken(){
        User testUser = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("test")
                .build());

        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        assertThat(userId).isEqualTo(testUser.getId());

    }

    @DisplayName("validToken() : 유효한 토큰인 때에 유효성 검증에 성공")
    @Test
    void validToken_validToken(){

        String token = JwtFactory.withDefaultValue().createToken(jwtProperties);

        boolean result = tokenProvider.validToken(token);

        assertThat(result).isTrue();
    }

    @DisplayName("validToken() : 만료된 토큰일 때에 유효성 검증 실패")
    @Test
    void validToken_invalidToken(){

        // 일부러 실패하는 Token 을 생성하기 때문에 위 메서드와 given 이 다름
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);
        // when
        boolean result = tokenProvider.validToken(token);

        assertThat(result).isFalse();
    }
    // 3. get
    @DisplayName("getAuthentication() : 토큰 기반으로 인증 정보 가져옴")
    @Test
    void getAuthentication(){
        String userEmail = "user@gmail.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        //when
        Authentication authentication = tokenProvider.getAuthentication(token);

        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);
                //\\
        // 참조 자료형 캐스팅 응용\\
    }
    //4 . getUserId()
    @DisplayName("getUserId() : 토큰으로 유저 ID 를 가져올 수 있다")
    @Test
    void getUserID (){
        Long userID = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id",userID))
                .build()
                .createToken(jwtProperties);

        Long userIdByToken = tokenProvider.getUserId(token);

        assertThat(userIdByToken).isEqualTo(userID);
    }

}
/*
    1. generateToken
        given : 토큰에 유저 정보를 추가하기 위한 테스트 유저생성
        when : 토큰 제공자의 generateToken() 메서드 호출
        then : jjwt 라이브러리 사용해서 토큰 복호화
            -> 토큰을 만들 때 클레임으로 넣어둔 id 값이 given 에서 만든
            유저 id 와 동일한지 확인

    2-1. validToken_validToken
        given : jjwt 라이브러리 사용, 토큰 생성 후 만료 시간은 현재 시간으로 부터 14일뒤로
            만료되지 않은 토큰으로 생성 -- Default 로 만듬
        when : 토큰 제공자 tokenProvider validToken 메서드를 호출해 유효한 토큰인지 검사 후
            결과 값 boolean 으로 반환
        then : 반환값 true 인지 확인

    2-2. validToken_invalidToken
        given : jjwt 라이브러리 사용, 토큰 생성 후 만료시간은 1970년 1월1일 부터
            현재 시간을 밀리초 단위로 치환 한 값(new Date().get time())에 1000을 빼서
            이미 만료된 토큰으로 설정
        when : 토큰 제공자 tokenProvider validToken 메서드를 호출해 유효한 토큰인지 검사 후
            결과 값 boolean 으로 반환
        then : 반환값 false 인지 확인

    4. getAuthentication
        given : jjwt 라이브러리 사용 토큰 생성 . subject 는 user@gamili.com
        when : getAuthentication 메서드 호출 인증 객체 반환
        then : 반환 받은 인증 객체의 유저 이동을 가져와서 given 에서 초기화 한
            subject 값이 user@gmail.com 과 일치하는지 확인

    4. getUserId
        given : jjwt 라이브러리 사용해 토큰 생성. 이때 클레임을 추가하여 키는 "id" 값은 1인
            유저 ID 생성
        when : getUserId() 호출해서 유저 ID 반환
        then : 반환 받은 유저 ID 가 given 에서 초기화 한 1과 같은지 확인

          리프레시 토큰 구현하기
            리프레시 토큰은 데이터베이스에 저장하는 정보이므로 엔티티와 리포터지토리를 추가해야 함.
            만들 엔티티와 매핑되는 테이블 구조는

           +--------------------------------------------------------------------------------+
           |    컬럼명         |   자료형          |    null 허용    |    키     |   설명
           ---------------------------------------------------------------------------------+
           | id              |    BIGINT        |       N       |   기본키    | 일련번호, 기본키
           | user_id          |   BIGINT        |       N       |           | 유지 ID
           | refresh_token    |   VARCHAR(255)  |       N       |           | 토큰값
           +--------------------------------------------------------------------------------+

 */