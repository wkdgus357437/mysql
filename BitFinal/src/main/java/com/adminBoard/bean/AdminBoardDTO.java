package com.adminBoard.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "adminboard") //테이블 명
@SequenceGenerator(name = "ADMINBOARD_SEQ_GENERATOR"
				 , sequenceName = "ADMINBOARD_SEQ"
				 , initialValue = 1
				 , allocationSize = 1
)
public class AdminBoardDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMINBOARD_SEQ_GENERATOR")
	@Column(name = "adminBoardSeq")
	private int adminBoardSeq;
	
	@Column(name = "title") // 게시판 글 제목
	private String title;

	@Column(name = "content") // 게시판 글 내용
	private String content;

	@CreationTimestamp // 엔티티가 생성되는 시점의 시간 등록 // logtime 컬럼
	private Timestamp logtime;

	
}
