package com.adminBoard.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adminBoard.bean.AdminBoardDTO;
import com.adminBoard.service.AdminBoardService;


@CrossOrigin
@RestController 
@RequestMapping(value = "adminBoard")
public class AdminBoardController {
	
	@Autowired
	private AdminBoardService adminBoardService;
	
	@PostMapping(path = "adminBoardWrite") // 관리자 글쓰기
	public void adminBoardWrite(@ModelAttribute AdminBoardDTO adminBoardDTO ) {
		adminBoardService.adminBoardWrite(adminBoardDTO);
	}

	@Secured("ROLE_ADMIN") // 관리자만 볼 수 있음, 관리자권한없으면 로그아웃시킴
	@GetMapping(path = "adminBoardList") //관리자 글 리스트
	public ResponseEntity<List<AdminBoardDTO>> adminBoardList(){
		List<AdminBoardDTO> list = adminBoardService.adminBoardList();
		return ResponseEntity.ok(list);
	}
	
	@PutMapping(path = "adminBoardUpdate") //관리자 글 수정
	public void adminBoardUpdate(@ModelAttribute AdminBoardDTO adminBoardDTO) {
		adminBoardService.adminBoardUpdate(adminBoardDTO);
	}
	
	@DeleteMapping(path = "adminBoardDelete") //관리자 글 삭제
	public void adminBoardDelete(@RequestParam String adminBoardSeq) {
		adminBoardService.adminBoardDelete(adminBoardSeq);
	}
	
	@GetMapping(path = "getAdminBoard")//관리자 게시판 글 수정 전 찾기
	public Optional<AdminBoardDTO> getAdminBoard(@RequestParam int adminBoardSeq){
		return adminBoardService.getAdminBoard(adminBoardSeq);
	}
	
	@GetMapping(path = "adminBoardSearch") // 관리자 게시판 타이틀+내용 검색
	public List<AdminBoardDTO> adminBoardSearch(@RequestParam Map<String, String> map){
		return adminBoardService.adminBoardSearch(map);
	}
}
