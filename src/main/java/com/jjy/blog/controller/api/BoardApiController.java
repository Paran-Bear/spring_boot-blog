package com.jjy.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jjy.blog.config.auth.PrincipalDetail;
import com.jjy.blog.dto.ResponseDto;
import com.jjy.blog.model.Board;
import com.jjy.blog.model.Reply;
import com.jjy.blog.model.User;
import com.jjy.blog.repository.BoardRepository;
import com.jjy.blog.repository.UserRepository;
import com.jjy.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board/{categoryId}")
	public ResponseDto<Integer> save(@PathVariable int categoryId,@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println(categoryId);
		boardService.글쓰기(board,principal.getUser(),categoryId);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);		
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/board/{id}/{categoryId}")
	public ResponseDto<Integer> update(@PathVariable int id,@PathVariable int categoryId,@RequestBody Board board){
		boardService.수정하기(id,categoryId,board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply,@AuthenticationPrincipal PrincipalDetail principal) {
//		if(principal.getUser()==null || principal.getUser().equals(""))
//		{
//			User user = userRepository.findByUsername("Test").orElseThrow(()->{
//				return new IllegalArgumentException("테스트 에러.");
//			});
//			principal.setUser(user);
//		}
		System.out.println(principal.getUser());
		boardService.댓글쓰기(principal.getUser(),boardId,reply);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		
		
		//데이터를 받을때 컨트롤러에서 dto를 만들어서 받는것도 좋다.
		//여기서 사용하지 않는 이유는
		//프로젝트 규모가 작아서 굳이 쓸 이유가 없었다.
		//프로젝트 규모가 크면 오고가는 데이터가 많아지고, 필드들이 많아진다.
		//Dto를 사용해서 받으면 함수가 받을 인수가 하나로 통일할수 있다.
		//dto장점-->내가 필요한 데이터를 한방에 받아서, 처리가 가능
		
		
		
		//또 하나의 댓글 처리 방법 --> 네이티브 쿼리
		
	}
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
		boardService.댓글삭제(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
		

}
