package com.main.bitfinal.memberService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.bitfinal.memberService.memberEntity.User;
import com.main.bitfinal.memberService.repository.MypageDAO;

import store.dao.StoreDAO;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	private MypageDAO mypageDAO;
	
	@Override
	public User getEditProfile(String username) {
		// TODO Auto-generated method stub
		return mypageDAO.findByUser(username);
	}

	@Override
	public void emailChange(String username, String email) {
		mypageDAO.emailChange(username, email);
		
	}

}
