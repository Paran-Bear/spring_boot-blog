package com.jjy.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjy.blog.model.Board;
import com.jjy.blog.model.User;
import org.springframework.data.jpa.repository.Query;

//DAO
//자동으로 bean등록
//@Repository<--생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {


	Optional<User> findByUsername(String username);
	Optional<User> findByNickname(String Nickname);
	Optional<User> findByEmail(String Email);
	
	//네이밍 쿼리
	//select * from user where username=1?; 1?<-- 인수.
	
}
//기본적인 CRUD는 이걸로 동작

	// JPA Naming 전략 - 네이밍 쿼리 전략
//	User findByUsernameAndPassword(String username, String password);
	// Select * FROM user WHERE username=?1 AND password=?2;<--이 쿼리가 동작함.
	// 각 물음표는 인수로 받을 수 있음.

//	@Query(value="Select * FROM user WHERE username=?1 AND password=?2",nativeQuery=true)
//	User login(String username,String password)
	// 똑같이 작동함.