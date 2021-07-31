package com.jjy.blog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjy.blog.config.auth.PrincipalDetail;
import com.jjy.blog.model.KakaoProfile;
import com.jjy.blog.model.OAuthToken;
import com.jjy.blog.model.User;
import com.jjy.blog.service.AdminService;
import com.jjy.blog.service.UserService;

//미인증 사용자들의 출입 경로 -->/auth/* 허용.
// 그냥 주소가 / 인 경로를 허용 --> index.jsp
//static이하 파일들 (/js/*, css/*, image/* 등등
//인증이 필요없는 페이지는 /auth
@Controller
public class AdminController {

	
	@Autowired
	private UserService userService;


	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/admin")
	public String Adminpage(Model model,@PageableDefault(size=99,sort="id",direction = Sort.Direction.ASC) Pageable pageable) {
		
		model.addAttribute("categorys", adminService.카테고리조회(pageable));
		model.addAttribute("users",userService.회원조회());
		return "admin/admin";

	}
	
	

	

	

}
