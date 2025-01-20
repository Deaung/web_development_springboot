package me.ahngeunsu.springbootdeveloper.config.oauth;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.ahngeunsu.springbootdeveloper.util.CookieUtil;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.web.util.WebUtils;

public class OAuth2AuthorizationRequestBasedOnCookieRepository
    implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public final static String OATH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oAuth2_auth_request";
    public final static int COOKIE_EXPIRE_SECOND = 18000;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {

        Cookie cookie = WebUtils.getCookie(request, OATH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        return CookieUtil.deserialize(cookie, OAuth2AuthorizationRequest.class);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        if (authorizationRequest == null){
            removeAuthorizationRequestCookie(request,response);
            return;
        }
        CookieUtil.addCookie(response,OATH2_AUTHORIZATION_REQUEST_COOKIE_NAME,
                CookieUtil.serialize(authorizationRequest),COOKIE_EXPIRE_SECOND);

    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request,
                                                                 HttpServletResponse response) {

        return null;
    }

    // 고유 메서드
    public void removeAuthorizationRequestCookie(HttpServletRequest request,
                                                HttpServletResponse response){
        CookieUtil.deleteCookie(request, response, OATH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
    }
}
/*
    config / OAuth2SuccessHandler
 */
