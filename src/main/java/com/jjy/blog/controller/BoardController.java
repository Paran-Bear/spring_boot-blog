package com.jjy.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjy.blog.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jjy.blog.config.auth.PrincipalDetail;
import com.jjy.blog.model.User;
import com.jjy.blog.repository.UserRepository;
import com.jjy.blog.service.AdminService;
import com.jjy.blog.service.BoardService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private AdminService adminService;

	@GetMapping("/board/category/{requiredCategory}")
	public String boardCategory(@PathVariable int requiredCategory, Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {// 컨트롤러에서 세션을
																											// 어떻게 찾는지..
		// model은 request정보 view까지 데이터를 끌고 이동.
		model.addAttribute("categoryNum",requiredCategory);
		model.addAttribute("boards", boardService.글목록(pageable));// view-resolver작동
		model.addAttribute("category", adminService.카테고리조회(pageable));

		// index.jsp로 boards 이름으로 데이터가 넘어감.
		return "board/board";
	}

	@GetMapping("/board")
	public String boardAll(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) throws JsonProcessingException {
		Page p=boardService.글목록(pageable);
		ObjectMapper objectMapper=new ObjectMapper();

		model.addAttribute("boards", boardService.글목록(pageable));// view-resolver작동
		model.addAttribute("categoryNum",0);
		model.addAttribute("category", adminService.카테고리조회(pageable));
		//objectMapper.writeValueAsString(p)
		return "board/board";
	}

	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model,
			@PageableDefault(size = 99, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		model.addAttribute("board", boardService.상세보기(id,false));
		model.addAttribute("category", adminService.카테고리조회(pageable));
		return "board/updateForm";
	}

	// user권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm(Model model,
			@PageableDefault(size = 99, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		model.addAttribute("category", adminService.카테고리조회(pageable));
		return "board/saveForm";
	}

	@GetMapping("/board/{id}")
	public String findById(@CookieValue(name="view") String cookie, HttpServletResponse response, @PathVariable int id, Model model,
						   @PageableDefault(size = 1, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		boolean hitup = false;
		System.out.println(cookie);
		if (!(cookie.contains(String.valueOf(id)))) {
			cookie += id + "/";
			hitup=true;
		}
		response.addCookie(new Cookie("view", cookie));
		Board board=boardService.상세보기(id,hitup);

		model.addAttribute("board", board);
		model.addAttribute("boards", boardService.글목록(pageable));
		model.addAttribute("totalElements", boardService.글목록(pageable).getNumber());
		return "board/detail";
	}
}
