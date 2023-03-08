package com.main.bitfinal.memberService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long tokenExpiresIn;

}
// 토큰 값 추출, 삽입할 때 쓰는 dto