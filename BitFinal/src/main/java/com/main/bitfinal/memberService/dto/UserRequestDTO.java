package com.main.bitfinal.memberService.dto;

import com.main.bitfinal.memberService.memberEntity.RoleType;
import com.main.bitfinal.memberService.memberEntity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    private String username;
    private String password;
    private String name;
    private String email;
    private String birth;
    private String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .roleType(RoleType.ROLE_ADMIN)
                .build();
    }
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
// Request 를 받을 dto
// UsernamePasswordAuthenticationToken 를 반환하여 아이디와 비밀번호가 일치하는지 검증하는 로직을 넣을 수 있게 된다.
