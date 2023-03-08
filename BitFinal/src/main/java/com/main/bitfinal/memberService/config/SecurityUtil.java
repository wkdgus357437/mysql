package com.main.bitfinal.memberService.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    private SecurityUtil() { }

    public static Long getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("시큐리티 전용 세션에 인증정보 없음");
        }
        return Long.parseLong(authentication.getName());
    }
}
// SecurityContext(스프링시큐리티 전용 세션) 에 유저 정보가 저장되는 시점을 다루는 클래스
// Request 가 들어오면 JwtFilter 의 doFilter 에서 저장되는데 거기에 있는 인증정보를 꺼내서, 그 안의 id 를 반환한다.
// Entity 를 정할때 id 의 타입을 Long 으로 했기 때문에 Long 을 반환한다.
