package com.main.bitfinal.memberService.service;

import com.main.bitfinal.memberService.memberEntity.User;

import store.bean.UserDTO;

public interface MypageService {

	public User getEditProfile(String username);

	public void emailChange(String username, String email);

}
