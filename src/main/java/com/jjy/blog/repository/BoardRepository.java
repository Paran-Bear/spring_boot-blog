package com.jjy.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjy.blog.model.Board;
import com.jjy.blog.model.User;

//DAO
//자동으로 bean등록
//@Repository<--생략 가능
public interface BoardRepository extends JpaRepository<Board, Integer> {
	
}
