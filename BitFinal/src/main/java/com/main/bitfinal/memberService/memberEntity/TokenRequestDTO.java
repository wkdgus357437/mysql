package com.main.bitfinal.memberService.memberEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenRequestDTO {

    private String accessToken;
    private String refreshToken;

}
