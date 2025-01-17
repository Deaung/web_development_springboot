package springbootdeveloper.config.jwt;

import io.jsonwebtoken.Jwts;
import me.ahngeunsu.springbootdeveloper.config.jwt.JwtProperties;
import me.ahngeunsu.springbootdeveloper.config.jwt.TokenProvider;
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

@SpringBootTest
class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProperties jwtProperties;

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
