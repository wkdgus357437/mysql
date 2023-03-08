package com.main.bitfinal.memberService.config;

import com.main.bitfinal.memberService.jwt.JwtAccessDeniedHandler;
import com.main.bitfinal.memberService.jwt.JwtAuthenticationEntryPoint;
import com.main.bitfinal.memberService.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) // 컨트롤러에서 각각의 메소드에 권한설정이 가능하게 해줌
@Component
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // request 로부터 받은 비밀번호를 암호화하기 위해 PasswordEncoder 빈 생성
    @Bean
    public BCryptPasswordEncoder encoderPwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable() // https 만을 사용하기위해 httpBasic disable
                .csrf().disable() // 리액트에서 token 을 localstorage 에 저장할 것이기 때문에 csrf disable
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // REST API 를 통해 세션 없이 토큰을 주고받으며 데이터를 주고받기 때문에 세션설정 STATELESS
                .and()
                .exceptionHandling() // 예외처리 => JwtAuthenticationEntryPoint, JwtAccessDeniedHandler 추가
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .authorizeRequests()
                .antMatchers("/adminindex/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfig(tokenProvider)) //JwtSecurityConfig 로 tokenProvider 적용
                .and()
                .build();
    }
}
