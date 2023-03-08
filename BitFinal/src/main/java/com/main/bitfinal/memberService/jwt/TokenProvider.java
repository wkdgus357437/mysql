package com.main.bitfinal.memberService.jwt;

import com.main.bitfinal.memberService.dto.TokenDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    // AUTHORITIES_KEY, BEARER_TYPE => 토큰을 생성하고 검증할 때
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 15; // 토큰의 만료 시간 15분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;
    private final Key key; //JWT 를 만들 때 사용하는 암호화 키값을 사용하기 위해 security 에서 불러왔다.

    // 생성자
    // application properties 에 적은 시크릿키값 인코딩하기
    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 토큰 생성하기
    // Authentication 인터페이스를 확장한 매개변수를 받아서 그 값을 string 으로 변환
    public TokenDTO generateTokenDto(Authentication authentication) {
        String authorites = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // 이후 현재시각과 만료시각을 만든 후 Jwts 의 builder 를 이용하여 Token 을 생성한 다음
        // TokenDto 에 생성한 token 의 정보를 넣는다.
        long now = (new Date()).getTime();

        Date tokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME); // 토큰 만료시간

        System.out.println("엑세스토큰 만료시간 : " + tokenExpiresIn + " => (이 시간까지 페이지 새로고침 없으면 만료되고 로그아웃 처리됨.)");

        String accessToken = Jwts.builder()
                .setExpiration(tokenExpiresIn)
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorites)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDTO.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .tokenExpiresIn(tokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .build();
    } // generateTokenDto


    // 토큰을 받았을 때 토큰의 인증을 꺼내는 메소드
    // parseClaims 메소드로 string 형태의 토큰을 claims 형태로 생성한다.
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보 없음"); // auth 가 없으면 exception 을 반환한다.
        }

        // GrantedAuthority 을 상속받은 타입만이 사용 가능한 Collection 을 반환한다.
        // 그리고 stream 을 통한 함수형 프로그래밍으로 claims 형태의 토큰을 알맞게 정렬한 이후
        // SimpleGrantedAuthority 형태의 새 List 를 생성한다. 여기에는 인가가 들어있다.
        // SimpleGrantedAuthority 은 GrantedAuthority 을 상속받았기 때문에 가능하다.
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // 이후 Spring Security 에서 유저의 정보를 담는 인터페이스인 UserDetails 에 token 에서 뽑은 정보와,
        // 아까 생성한 인가를 넣고, 이를 다시 UsernamePasswordAuthenticationToken 안에 인가와 같이 넣고 반환한다.
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);

        // 여기서 UsernamePasswordAuthenticationToken 인스턴스는
        // UserDetails 를 생성해서 후에 SecurityContext 에 사용하기 위해 만든 절차라고 이해하면 된다.
        // SecurityContext 는 Authentication 객체를 저장하기 때문이다.
        // (스프링 시큐리티 전용 세션을 만들기 위해 무조건 해야함...)
    }

    public boolean validateToken(String token) { // 토큰을 검증하기 위한 메소드다.
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
    // 토큰을 claims 형태로 만드는 메소드다.
    // 이를 통해 위에서 권한 정보가 있는지 없는지 체크가 가능하다.
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
