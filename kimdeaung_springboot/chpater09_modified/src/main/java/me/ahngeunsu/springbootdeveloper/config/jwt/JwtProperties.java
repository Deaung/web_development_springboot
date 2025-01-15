package me.ahngeunsu.springbootdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String issuer;
    private String secretKey;

    /*
        이렇게 하ㅕㅁㄴ issuer 필드에서 application.yml 에서 설정한 jwt.issuer 값이
        secretKey 에는 jwt.secret_key 값이 매핑


     */
}
