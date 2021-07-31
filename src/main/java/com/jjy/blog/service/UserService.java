package com.jjy.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjy.blog.model.RoleType;
import com.jjy.blog.model.User;
import com.jjy.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 해서 bean에 등록함(IoC해줌.)
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional // 하나의 메소드를 하나의 트랜젝션으로 묶여서, 실패를 하면 롤백을 한다.(롤백 코드도 짜야댐)
	public void 회원가입(User user) {

		String rawPassword = user.getPassword();// 원문
		String encPassword = encoder.encode(rawPassword);// 해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);

	}

	@Transactional
	public void 회원수정(User user) {
		// 수정할때는 영속성 컨텍스트 User오브젝트를 영속화 하고, 영속화된 User오브젝트를 수정
		// Select해서 User오브젝트를 DB로 가져오는 이유는 영속화 하기 위해
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update쿼리를 실행한다..
		User persistence = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원을 찾을수 없음.");
		});
		if (persistence.getOauth() == null || persistence.getOauth().equals("")) {
			if (user.getPassword() == null || user.getPassword().equals("")) {
				System.out.println("비밀번호 입력 없음.");
			} else {

				System.out.println(persistence.getUsername());
				System.out.println(persistence.getPassword());
				System.out.println(persistence.getNickname());
				System.out.println(persistence.getEmail());
				String rawPassword = user.getPassword();
				String encPassword = encoder.encode(rawPassword);
				persistence.setPassword(encPassword);
			}
			persistence.setEmail(user.getEmail());
			
		}
		else {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistence.setPassword(encPassword);
			persistence.setPassword(user.getPassword());
			persistence.setEmail(user.getEmail());
		}
		persistence.setNickname(user.getNickname());
		
		
		
		
		
		// 회원 수정 함수 종료시 --> 서비스 종료 --> 트랜잭션 종료 --> commit실행.
		// 영속화된 persistence객체의 변화가 감지되면 더티체킹해서 변화된 것들을 update쿼리 실행.

	}

	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});

		return user;

	}

	public List<User> 회원조회() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
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
