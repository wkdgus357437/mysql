package com.main.bitfinal.memberService.dto;

import com.main.bitfinal.memberService.memberEntity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO { // Response 를 보낼때 쓰이는 dto

    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String birth;
    private String phoneNumber;
    private String createDate;
    private String roleType;

    public static UserResponseDTO of(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .phoneNumber(user.getPhoneNumber())
                .createDate(String.valueOf(user.getCreateDate()))
                .roleType(String.valueOf(user.getRoleType()))
                .build();
    }

}
