package com.jjy.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jjy.blog.config.auth.PrincipalDetail;
import com.jjy.blog.model.User;
import com.jjy.blog.repository.UserRepository;
import com.jjy.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	
	
	
	@GetMapping({"","/"})
	public String index(Model model, @PageableDefault(size=10,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {//컨트롤러에서 세션을 어떻게 찾는지..
		//model은 request정보 view까지 데이터를 끌고 이동.
		model.addAttribute("boards",boardService.글목록(pageable));//view-resolver작동
		//index.jsp로 boards 이름으로 데이터가 넘어감.
		return "index";
	}
	
	
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.상세보기(id));
		return "board/updateForm";
	}
	
	//user권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id,Model model,@PageableDefault(size=1,sort="id",direction = Sort.Direction.ASC) Pageable pageable) {
		model.addAttribute("board", boardService.상세보기(id));
		model.addAttribute("boards", boardService.글목록(pageable));
		model.addAttribute("totalElements", boardService.글목록(pageable).getNumber());
		return "board/detail";
	}
}
