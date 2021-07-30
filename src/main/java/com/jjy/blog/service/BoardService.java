package com.jjy.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjy.blog.model.Board;
import com.jjy.blog.model.Category;
import com.jjy.blog.model.Reply;
import com.jjy.blog.model.RoleType;
import com.jjy.blog.model.User;
import com.jjy.blog.repository.BoardRepository;
import com.jjy.blog.repository.CategoryRepository;
import com.jjy.blog.repository.ReplyRepository;
import com.jjy.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 해서 bean에 등록함(IoC해줌.)
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Transactional // 하나의 메소드를 하나의 트랜젝션으로 묶여서, 실패를 하면 롤백을 한다.(롤백 코드도 짜야댐)
	public void 글쓰기(Board board, User user, int categoryId) {// title, content
		Category category=categoryRepository.findById(categoryId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 카테고리를 찾을수 없습니다.");
		});
		
		
		board.setCategory(category);
		
		board.setCount(0);
		
		board.setUser(user);
		
		boardRepository.save(board);
		

	}

	@Transactional(readOnly=true)
	public Page<Board> 글목록(Pageable pageable) {

		return boardRepository.findAll(pageable);

	}
	@Transactional(readOnly=true)
	public Page<Board> 글목록(int category,Pageable pageable) {

		return boardRepository.findAllByCategory(category,pageable);

	}
	

	@Transactional(readOnly=true)
	public Board 상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을수 없습니다.");
		});
		// TODO Auto-generated method stub

	}

	@Transactional
	public void 삭제하기(int id) {
		boardRepository.deleteById(id);

	}

	@Transactional
	public void 수정하기(int id, int categoryId, Board requestBoard) {
		Board board=boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("해당 글을 찾을수 없습니다.");
				});//영속화 완료
		Category category=categoryRepository.findById(categoryId).orElseThrow(()->{
			return new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.");
		});
		board.setCategory(category);
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당함수로 종료시 (Service가 종료될 때) 트랜잭션이 종료.
		//이 떄 더티체킹을 함. 달라진 데이터를 자동으로 업데이트해줌.
		//DB Flush
	}

	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply requestReply) {
		
		
		Board board = boardRepository.findById(boardId).orElseThrow(()->{
			return new IllegalArgumentException("댓글 작성 실패 : 해당 게시글이 존재하지 않습니다.");
		});//영속화 완료
		
		
		requestReply.setUser(user);
		
		requestReply.setBoard(board);
		
		replyRepository.save(requestReply);
	}

	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
		
	}

//	@Transactional(readOnly=true)//Select 할 때 트랜젝션 시작, 서비스 종료시 트랜젝션 종료(정합성 유지)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}

}

//Service가 필요한 이유??
//1.트랜젝션을 관리하기 위해서.
//2.서비스의 의미? -> CRUD를 비롯해 모든 과정의 프로세스를 하나의 '서비스'라고 한다.
//							-> 여러개의 트랜젝션으로 이루어져 있음.
// 						->ex) 과정 1을 성공하고, 과정2를 실패하면, 모든 과정을 롤백해야한다. 이를 위해서 서비스를 사용한다.
