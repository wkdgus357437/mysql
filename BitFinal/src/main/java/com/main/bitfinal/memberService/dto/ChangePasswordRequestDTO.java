package com.main.bitfinal.memberService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequestDTO {
    private String username;
    private String exPwd;
    private String newPwd;
}
// 비밀번호를 수정할 때 쓰이는 dto  이전의 비밀번호가 제대로 입력하지 않는다면 실행되지 않는다.
