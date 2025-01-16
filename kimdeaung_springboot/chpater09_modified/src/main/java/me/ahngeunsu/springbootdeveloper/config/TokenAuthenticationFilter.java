package me.ahngeunsu.springbootdeveloper.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.config.jwt.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request;
            HttpServletResponse response;
            FilterChain filterChain
    )throws ServletException, IOException{

        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        String token = getAccessToken(authorizationHeader);
        if(tokenProvider.validToken(token)){
            Authentication authentication = tokenProvider.getAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
    private String getAccessToken(String authorizationHeader){
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)){
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

}
/*
    요청 헤더에서 키가 'Authorization' 인 필드의 값을 가져온 다음 토큰의 접두가 Bearer 를 제외한 값을 얻는다
    만약 값이 null 이거나  Bearer 로 시작되지 않으면 null 을 변환
    이어서 가져온 토큰이 유효한지 확인하고 유효 하다면 인증 정보를 관리하는 시큐리티 컨텍스트에 인증정보 설정
    위에서 작성한 코드가 실행되며 인증정보가 설정된 이후 컨텍스트 홀더에 getAuthentication 메서드 사용해
    인증 정보를 가져오면 유저 객체가 변환
    유저 객체에는 유저이름과 권한 목록 과 같은 인증정보 포함

    토큰 관련 API 구현
        리프레시 토큰을 전달 받아 검증 후 유효한 리프레시 토큰이라면 새로운 엑세스 토큰을 생성하는 토큰 api 구현
        토큰 서비스 토큰 컨트롤러를 차례로 구현 예정
 */
