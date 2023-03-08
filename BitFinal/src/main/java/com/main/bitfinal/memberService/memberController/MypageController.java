package com.main.bitfinal.memberService.memberController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.bitfinal.memberService.memberEntity.User;
import com.main.bitfinal.memberService.service.MypageService;

import store.bean.UserDTO;

@CrossOrigin ("http://localhost:3000")
@RestController //@RestController = @Controller + @ResponseBody
@RequestMapping(value = "myPage")
public class MypageController {
	@Autowired
	private MypageService mypageservice;
	
	//usertable 이용
		@GetMapping(path = "getEditProfile")
		public User getEditProfile(@RequestParam String username ) {
			System.out.println(username);
			return mypageservice.getEditProfile(username);
		}
		
		@GetMapping(path = "emailChange")
		public void emailChange(@RequestParam String username, @RequestParam String email) {
			System.out.println(email);
		     mypageservice.emailChange(username, email);
		}

}
