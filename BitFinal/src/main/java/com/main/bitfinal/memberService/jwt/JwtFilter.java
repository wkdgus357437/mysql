package com.main.bitfinal.memberService.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;

    // Request Header 에서 토큰 정보를 꺼내오는 메소드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7); // 문자열 0부터 7번째까지 자르고 그 뒤에 토큰값만 리턴하기
        }
        return null;
    }

    // 필터링을 실행하는 메소드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = resolveToken(request);

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}

// resolveToken 을 통해 토큰 정보를 꺼내온 다음, validateToken 으로 토큰이 유효한지 검사를 해서,
// 만약 유효하다면 Authentication 을 가져와서 SecurityContext 에 저장한다. (시큐리티 전용 세션 저장)
// SecurityContext 에서 허가된 uri 이외의 모든 Request 요청은 전부 이 필터를 거치게 되며,
// 토큰 정보가 없거나 유효치않으면 정상적으로 수행되지 않는다.
// 반대로 Request 가 정상적으로 Controller 까지 도착했으면 SecurityContext(세션) 에 ID가 존재한다는 것이 보장이 된다.
