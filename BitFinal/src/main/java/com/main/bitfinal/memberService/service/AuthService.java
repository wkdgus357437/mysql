package com.main.bitfinal.memberService.service;

import com.main.bitfinal.memberService.dto.TokenDTO;
import com.main.bitfinal.memberService.dto.UserRequestDTO;
import com.main.bitfinal.memberService.jwt.TokenProvider;
import com.main.bitfinal.memberService.memberEntity.RefreshTokenDTO;
import com.main.bitfinal.memberService.memberEntity.RoleType;
import com.main.bitfinal.memberService.memberEntity.TokenRequestDTO;
import com.main.bitfinal.memberService.memberEntity.User;
import com.main.bitfinal.memberService.repository.RefreshTokenRepository;
import com.main.bitfinal.memberService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    // 로그인 메소드
    // userRequestDto 에 있는 메소드 toAuthentication 를 통해 생긴
    // UsernamePasswordAuthenticationToken 타입의 데이터를 가지게된다.
    // 주입받은 Builder 를 통해 AuthenticationManager 를 구현한 ProviderManager 를 생성한다.
    // 이후 ProviderManager 는 데이터를 AbstractUserDetailsAuthenticationProvider 의 자식 클래스인
    // DaoAuthenticationProvider 를 주입받아서 호출한다.
    // DaoAuthenticationProvider 내부에 있는 authenticate 에서 retrieveUser 를 통해
    // DB 에서의 User 의 비밀번호가 실제 비밀번호가 맞는지 비교한다.
    // retrieveUser 에서는 DB 에서의 User 를 꺼내기 위해,
    // CustomUserDetailService 에 있는 loadUserByUsername 을 가져와 사용한다.
    // 여기서 CustomUserDetailsService 에서 오버라이딩한 loadUserByUsername 이 사용된다.

    @Transactional
    public TokenDTO login(UserRequestDTO requestDTO) {
        // ID/PW => AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = requestDTO.toAuthentication();
        // 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에 loadUserByUsername 실행됨
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        // 인증 정보로 JWT 생성
        TokenDTO tokenDTO = tokenProvider.generateTokenDto(authentication);

        RefreshTokenDTO refreshTokenDTO = RefreshTokenDTO.builder()
                .key(authentication.getName())
                .value(tokenDTO.getRefreshToken())
                .build();

        System.out.println(refreshTokenDTO.getKey() + " " + refreshTokenDTO.getValue());

        // 리프레시 토큰, 유저 id 값 refresh_token 테이블에 세이브
        refreshTokenRepository.save(refreshTokenDTO);

        return tokenDTO;

    }

    @Transactional
    public TokenDTO reIssue(TokenRequestDTO tokenRequestDTO) {

        // 서버에서 넘어온 리프레시 토큰 비교
        if (!tokenProvider.validateToken(tokenRequestDTO.getRefreshToken())) {
            throw new RuntimeException("refreshToken 이 유효하지 않습니다.");
        }
        // Access Token 에서 ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDTO.getAccessToken());

        // DB 에서 ID랑 key 매칭 후 Refresh Token 값 가져옴
        RefreshTokenDTO refreshTokenDTO = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 해서 사라짐"));

        // 검사
        if (!refreshTokenDTO.getValue().equals(tokenRequestDTO.getRefreshToken())) {
            throw new RuntimeException("리프레시 토큰이 일치하지 않음!");
        }

        // 일치하면 새로운 토큰 발급 후 쿠키와 리덕스에 새로운 토큰 저장
        TokenDTO tokenDTO = tokenProvider.generateTokenDto(authentication);

        // DB 에 업데이트
        RefreshTokenDTO NewRefreshTokenDTO = refreshTokenDTO.updateValue(tokenDTO.getRefreshToken());
        refreshTokenRepository.save(NewRefreshTokenDTO);

        System.out.println("페이지 새로고침 => 엑세스토큰, 리프레시토큰 재발급 => (리프레시 토큰은 업데이트 쿼리 실행, 다른 사람이 같은 아이디로 로그인 하면 재발급한 리프레시 토큰과 DB에 있는 리프레시토큰이 일치하지 않기 때문에 재발급 불가)");

        // 토큰 발급
        return tokenDTO;

    }

    @Transactional
    public void signup(User user) {
        userRepository.save(user);
    }

    public void changeRoleType() {
        User user = new User();
        user.setRoleType(RoleType.ROLE_ADMIN);
        userRepository.save(user);
    }

}
