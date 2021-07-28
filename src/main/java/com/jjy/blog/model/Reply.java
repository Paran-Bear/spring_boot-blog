package com.jjy.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User  클래스가 MySql에 자동으로 테이블 생성.
public class Reply {
	
	@Id //Primary Key
	@GeneratedValue(strategy=GenerationType.IDENTITY)//프로젝트와 연결된 DB의 넘버링 전략을 따라간다.
	private int id;//시퀀스, auto_increment
	
	@Column(nullable = false,length=200)
	private String content;//섬머노트 라이브러리 사용-> <html>태그가 섞여서 디자인 됨.
	
	@ManyToOne // 하나의 글에 여러 댓글을 달 수 있음.
	@JoinColumn(name="boardId")
	private Board board;
	// board가 OneToMany의 Mapped By =" "<--에 들어감.
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	

	
	
	
	@CreationTimestamp
	private Timestamp createDate;

}
