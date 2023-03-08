package com.main.bitfinal.memberService.memberEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Table(name = "refresh_token")
@Entity
public class RefreshTokenDTO {

    @Id
    @Column(name = "rt_key")
    private String key; // username 들어감

    @Column(name = "rt_value")
    private String value; // 리프레시 토큰 들어감

    @Builder
    public RefreshTokenDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RefreshTokenDTO updateValue(String token) {
        this.value = token;
        return this;
    }
}
// Refresh Token 저장소
// Refresh Token 유효성검증을 위해 RDB 에 저장
// 보통은 Token 이 만료될 때 자동으로 삭제 처리 하기 위해 Redis 를 많이 사용하지만, 임시로 RDB 에 저장하는 방식으로 구현
// RDB 를 저장소로 사용한다면 배치 작업을 통해 만료된 토큰들을 삭제해주는 작업이 필요
