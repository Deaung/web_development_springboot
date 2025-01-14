package me.kimdeaung.springbootdeveloper.config.WebSecurity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kimdeaung.springbootdeveloper.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailService userDetailService;

    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{

        return http.authorizeHttpRequests(auth->auth.requestMatchers(
                new AntPathRequestMatcher("/login"),
                new AntPathRequestMatcher("/signup"),
                new AntPathRequestMatcher("/user")
            ).permitAll()
                .anyRequest().authenticated())
                .formLogin(formLogin ->formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/articles")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService)throws Exception {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }
    // 9 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
/*
    1. 스프링 시큐리티의 모든 기능 사용하지 않게 설정하는 코드
        즉 인증 및 인가 서비스를 모든곳에 적용하지는 않음
        일반적으로 정적 리소스 (이미지,html 파일) 에 설정
        정적 리소스만 스프링 시큐리티 사용을 빙활성화 하는데 static 하위 경로에 있는 리소스와
        h2 데이터를 확인하는 데 사용하는 h2-console 하위 url 대상으로 .ignoring() 메서드도 작성

        http 요청에 대한 웹 기반 보안을 구성 이 메서드에서 인증 인가 및 로그인 로그아웃

        특정 경로 엑세스 설정
            requestMatcher() 특정 요청과 일치하는 url 에 대한 엑세스를 설정
            permitAll() 누구나 접근 가능하게 설정 /인가 및 인증 없이 접근 가능
            anyRequest() 위에서 설정한 url 이외의 요청에 대해 설정
            authenticated() 별도의 인가 필요하지 않지만 인증이 성공된 상태여야 접근 가능

        폼 기반 로그인 설정
            loginPage()  로그인 페이지; 경로를 설정
            defaultSuccessUrl() 로그인 완료시 이동할 경로 설정

        로그아웃 설정
            logoutSuccessUrl 로그아웃 완료시 이동할 경로 설정
            invalidHttpSession() 로그아웃 이후 세션을 전체 삭제 할지 여부 설정

        csrf 설정 비활성화 csrf 공격을 방지하기 위해 설정 실습 편하게 하기 위해 비활성화

        인증관리자 설정, 사용자 정보 가져올 서비스 재정의 인증방법 예를 들어
        LADP, JDBD 기방 인증 설정시 사용

        사용자 서버 설정
            userDetailService 사용자 정보를 가져올 서비스 설정
            이때 설정하는 서비스 클래스는 반드시
                userDetailsService 를 상속받은 클래스여야만함
            passwordEncoder : 비밀번호 인코더 설정

        패스워드 인코더를 빈으로 등록

        회원가입 구현
            서비스 메서드 작성 - 사용자 정보 담은 객체 생성
                -> dto 패키지 addUserRequest.java 생성




 */
