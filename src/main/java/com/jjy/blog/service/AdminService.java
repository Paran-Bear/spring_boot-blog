package com.jjy.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjy.blog.model.Board;
import com.jjy.blog.model.Category;
import com.jjy.blog.model.RoleType;
import com.jjy.blog.model.User;
import com.jjy.blog.repository.BoardRepository;
import com.jjy.blog.repository.CategoryRepository;
import com.jjy.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 해서 bean에 등록함(IoC해줌.)
public class AdminService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});

		return user;

	}
	
	@Transactional(readOnly = true)
	public Page<Category> 카테고리조회(Pageable pageable) {		
		return categoryRepository.findAll(pageable);		
	}
	
	
	@Transactional
	public void 카테고리추가(Category category) {
		//category.setName(cname);
		//categoryRepository.save(category);
		categoryRepository.save(category);		
	}
	
	@Transactional
	public void 카테고리변경(Category RequestedCategory) {
		// TODO Auto-generated method stub
		
		Category category=categoryRepository.findById(RequestedCategory.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("해당 글을 찾을수 없습니다.");
				});//영속화 완료
		category.setName(RequestedCategory.getName());		
	
		
	}

	public void 카테고리삭제(int id) {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
	}

}
