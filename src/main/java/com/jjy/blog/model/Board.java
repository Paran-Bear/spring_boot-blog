package com.jjy.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //오토 인크리먼트
	private int id;
	
	@Column(nullable = false,length =100)
	private String title;
	
	@Lob//대용량 데이터 사용할 때 사용.
	private String content;//섬머노트 라이브러리 사용-> <html>태그가 섞여서 디자인 됨.
	
	//@ColumnDefault("0")//int 타입이라서 ' ' 사용하지 않음.
	private int count;//조회수
	
	
	@JoinColumn(nullable = true,name="category")
	private String category;
	
	
	@ManyToOne //다대일 관계 (한명의 유저는 여러 게시글을 작성 가능)
	@JoinColumn(name="userId")//Board 테이블에 작성되는 피드 명
	private User user;
	//DB는 오브젝트를 저장 할 수 없어서 FK를 쓰지만, 자바는 오브젝트를 저장 가능하다.
	//User 오브젝트를 사용하기 때문에 자동으로 DB에는 FK가 생성됨.
	
	
	
//	@JoinColumn(name="replyId") 하나의 컬럼에 여러 값(여러개의 댓글)을 넣으면 1정규화가 깨지므로 안씀.
	@OneToMany(mappedBy = "board",fetch=FetchType.EAGER,cascade = CascadeType.REMOVE) //연관 관계의 주인이 해당 오브젝트가 아님을 의미. FK가 아님. DB에 칼럼 안만듬
	@JsonIgnoreProperties({"board"})
	@OrderBy("id asc")
	private List<Reply> replys; //컬렉션 리스트 사용.
	//OneToMany에서 fetch=FetchType.EAGER 와 LAZY가 있다. EAGER은 디폴트로, 무조건 같이 불러오고
	//LAZY는 필요할 때 만 불러온다.
	
	@CreationTimestamp
	private Timestamp createDate;

}
