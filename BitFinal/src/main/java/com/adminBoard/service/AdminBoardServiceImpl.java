package com.adminBoard.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adminBoard.bean.AdminBoardDTO;
import com.adminBoard.dao.AdminBoardDAO;

@Service
public class AdminBoardServiceImpl implements AdminBoardService {

	@Autowired
	private AdminBoardDAO adminBoardDAO;
	
	@Override
	public void adminBoardWrite(AdminBoardDTO adminBoardDTO) { // 관리자 게시판 글쓰기
		adminBoardDAO.save(adminBoardDTO);
		
	}

	@Override
	public List<AdminBoardDTO> adminBoardList() { // 관리자 게시판 글 리스트
		return adminBoardDAO.findAll();
	}
	
	@Override
	public Optional<AdminBoardDTO> getAdminBoard(int adminBoardSeq) { //관리자 게시판 글 수정 전 찾기
		return adminBoardDAO.findBygetAdminBoard(adminBoardSeq);
	}
	
	@Override
	public void adminBoardUpdate(AdminBoardDTO adminBoardDTO) { //관리자 게시판 글 수정
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		adminBoardDTO.setLogtime(timestamp);
		adminBoardDAO.save(adminBoardDTO);
	}

	@Override
	public void adminBoardDelete(String adminBoardSeq) { //관리자 게시판 글 삭제
//		adminBoardDAO.deleteById(adminBoardSeq);
		adminBoardDAO.deleteById(adminBoardSeq);
	}
	
	
	// 관리자 게시판 글 검색 타이틀+내용
	@Override
	public List<AdminBoardDTO> adminBoardSearch(Map<String, String> map) {
		String adminSearchOption = map.get("adminSearchOption");
		String adminKeyword = map.get("adminKeyword");
		
		if(adminSearchOption.equals("title"))
		return adminBoardDAO.getAdminSearchTitle(adminKeyword);
		else
			return adminBoardDAO.getAdminSearchContent(adminKeyword);
	}

}
