package com.jjy.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM --> 프로그래밍 언어들의 오브젝트를 DB와 매핑해줌.

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert//Null 필드를 자동으로 제외하고 insert실행
@Entity //User  클래스가 MySql에 자동으로 테이블 생성.
public class User {
	@Id //Primary Key
	@GeneratedValue(strategy=GenerationType.IDENTITY)//프로젝트와 연결된 DB의 넘버링 전략을 따라간다.
	private int id;//시퀀스, auto_increment
	
	@Column(nullable=false,length=100, unique = true)//널 비허용, 길이 제한 30
	private String username;//유저 ID
	
	@Column(nullable=false,length=100, unique = true)//널 비허용, 길이 제한 30
	private String nickname;//유저 닉네임
	
	@Column(nullable=false,length=100)//Null (카카오 로그인 같은 경우 Null이 될 수도 있음.), 길이 제한 100, 해쉬로 비밀번호를 암호화 하기 때문에 길이 제한 크게
	private String password;
	
	@Column(nullable=false,length=50)//널 비허용, 길이 제한 50
	private String email;
	
//	@ColumnDefault("'user'")// ' ' 사용에 주의.
	@Enumerated(EnumType.STRING)//이넘을 사용하고, 타입은 스트링 을 명시.
	private RoleType role; // Enum쓰는게 좋다. 데이터의 도메인을 만들 수 있다.
	//admin, user, manager 권한을 준다 (오타를 방지하기 위해 Enum(일종의 범위 지정)사용)
		
	private String oauth;

	
	@CreationTimestamp  //시간 자동 입력
	private Timestamp createDate; 

}
