package com.jjy.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jjy.blog.config.auth.PrincipalDetail;
import com.jjy.blog.config.auth.PrincipalDetailService;
import com.jjy.blog.dto.ResponseDto;
import com.jjy.blog.model.Board;
import com.jjy.blog.model.Category;
import com.jjy.blog.model.RoleType;
import com.jjy.blog.model.User;
import com.jjy.blog.repository.CategoryRepository;
import com.jjy.blog.service.AdminService;
import com.jjy.blog.service.UserService;

@RestController
public class AdminApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	
	@PostMapping("/api/category")
	public ResponseDto<Integer> save(@RequestBody Category category) {
		adminService.카테고리추가(category);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	@PutMapping("/api/category")
	public ResponseDto<Integer> update(@RequestBody Category category) {			
		adminService.카테고리변경(category);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	@DeleteMapping("/api/category/{categoryId}")
	public ResponseDto<Integer> delete(@PathVariable int categoryId) {			
		adminService.카테고리삭제(categoryId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
