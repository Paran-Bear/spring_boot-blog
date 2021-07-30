package com.jjy.blog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jjy.blog.model.Board;
import com.jjy.blog.model.User;

//DAO
//자동으로 bean등록
//@Repository<--생략 가능
public interface BoardRepository extends JpaRepository<Board, Integer> {
	@Query(value="select * from board where categoryId=?1",nativeQuery = true)
	<T> Page<T> findAllByCategory(int categoryId,Pageable pageable);
}
