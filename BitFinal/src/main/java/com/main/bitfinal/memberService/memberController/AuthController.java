package com.main.bitfinal.memberService.memberController;

import com.main.bitfinal.memberService.dto.TokenDTO;
import com.main.bitfinal.memberService.dto.UserRequestDTO;
import com.main.bitfinal.memberService.memberEntity.RoleType;
import com.main.bitfinal.memberService.memberEntity.TokenRequestDTO;
import com.main.bitfinal.memberService.memberEntity.User;
import com.main.bitfinal.memberService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입 매핑
    @PostMapping(path = "signup")
    public void signUp(@ModelAttribute User user) {
        user.setRoleType(RoleType.ROLE_USER);
        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        authService.signup(user);
    }

    // 로그인 매핑
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserRequestDTO requestDTO) {
        System.out.println(requestDTO.getUsername());
        return ResponseEntity.ok(authService.login(requestDTO));
    }

    // 토큰 재발급 매핑
    @PostMapping(path = "/reIssue")
    public ResponseEntity<TokenDTO> reIssue(@RequestBody TokenRequestDTO tokenRequestDTO) {
        return ResponseEntity.ok(authService.reIssue(tokenRequestDTO));
    }

}

