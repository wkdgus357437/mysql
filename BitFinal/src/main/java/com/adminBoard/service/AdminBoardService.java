package com.adminBoard.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.adminBoard.bean.AdminBoardDTO;

public interface AdminBoardService {

	public void adminBoardWrite(AdminBoardDTO adminBoardDTO); //관리자 게시판 글쓰기

	public List<AdminBoardDTO> adminBoardList(); //관리자 게시판 목록
		
	public void adminBoardUpdate(AdminBoardDTO adminBoardDTO); //관리자 게시판 글 수정

	public void adminBoardDelete(String adminBoardSeq); //관리자 게시판 삭제

	public Optional<AdminBoardDTO> getAdminBoard(int adminBoardSeq);//관리자 게시판 글 수정 전 찾기

	public List<AdminBoardDTO> adminBoardSearch(Map<String, String> map); //관리자 게시판 검색


	
}
