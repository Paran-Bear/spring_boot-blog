package com.jjy.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jjy.blog.model.Board;
import com.jjy.blog.model.Category;
import com.jjy.blog.model.User;

//DAO
//자동으로 bean등록
//@Repository<--생략 가능
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query(value="select name from category",nativeQuery = true)
	public List<String> findAllByName();

	@Query(value="select id from category where id='%'?1'%'",nativeQuery = true)
	public int findIdByName(String name);
	
	
}
