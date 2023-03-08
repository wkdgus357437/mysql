package com.main.bitfinal.memberService.service;

import com.main.bitfinal.memberService.config.SecurityUtil;
import com.main.bitfinal.memberService.dto.UserResponseDTO;
import com.main.bitfinal.memberService.memberEntity.User;
import com.main.bitfinal.memberService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 조회만 가능 =>  성능 최적화(등록, 수정, 삭제 동작 불가 하지만 영속성 컨텍스트의 변경감지를 위한 스냅샷을 보관하지 않아 성능이 향상된다.)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO getMyInfoBySecurity() { // 헤더에 있는 token 값 체크 후 유저정보를 리턴하는 메소드
        return userRepository.findById(SecurityUtil.getCurrentUserId())
                .map(UserResponseDTO::of)
                .orElseThrow(() -> new RuntimeException("로그인이 확인되지 않음"));
    }

    @Transactional
    public UserResponseDTO changeUsername(String username, String name) { // 이름 변경
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("로그인이 확인되지 않음"));
        user.setName(name);
        return UserResponseDTO.of(userRepository.save(user));
    }

    @Transactional // 비밀번호 변경
    public UserResponseDTO changeMemberPassword(String exPwd, String newPwd) {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        if (!passwordEncoder.matches(exPwd, user.getPassword())) {
            throw new RuntimeException("비밀번호가 맞지 않습니다");
        }
        user.setPassword(passwordEncoder.encode((newPwd)));
        return UserResponseDTO.of(userRepository.save(user));
    }


    @Transactional
    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }
}

// @Transactional => Transactional = 거래
// 일련의 작업들을 묶어서 하나의 단위로 처리 ( 데이터베이스에서 데이터를 넣고 빼고 할때 하나라도 잘못되면(거래가 잘못되면) 처음으로 되돌려줌.(거래 사기 방지)
