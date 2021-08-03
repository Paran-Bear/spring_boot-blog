package com.jjy.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.jjy.blog.config.auth.PrincipalDetail;
import com.jjy.blog.config.auth.PrincipalDetailService;
import com.jjy.blog.dto.ResponseDto;
import com.jjy.blog.model.RoleType;
import com.jjy.blog.model.User;
import com.jjy.blog.service.UserService;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Value("${cos.key}")
    private String cosKey;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {// username,password,email
//				
        int status = HttpStatus.OK.value();


//			
        // user.setEmail("asdfasdf");
        userService.회원가입(user);
//		
        return new ResponseDto<Integer>(status, 1);

        // Jackson 라이브러리가 자바 오브젝트를 JSON으로 변환해서 리턴해줌.
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) {

        userService.회원수정(user);

        // 회원 수정 직후는 트랜잭션이 종료(DB는 commit)되지만,
        // 세션 변경이 안되어 있음.
        // 직접 세션을 재생성.

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    @PostMapping("/auth/usernamecheck/{username}")
    public ResponseDto<Integer> checkUsername(@PathVariable String username) {
        System.out.println(username);
        int status = HttpStatus.OK.value();
        User user = userService.회원찾기(username);
        System.out.println(user.getId());
        if (user.getId() != 0) {
            status = -1;
        }
        return new ResponseDto<Integer>(status, 1);
    }

    @PostMapping("/auth/nicknamecheck/{nickname}")
    public ResponseDto<Integer> checkNickname(@PathVariable String nickname) {
        System.out.println(nickname);
        int status = HttpStatus.OK.value();
        User user = userService.회원닉네임찾기(nickname);
        System.out.println(user.getId());

        if (user.getId() != 0) {
            status = -1;
        }
        return new ResponseDto<Integer>(status, 1);
    }

    @PostMapping("/auth/emailcheck/{email}")
    public ResponseDto<Integer> checkMail(@PathVariable String email) {
        System.out.println(email);
        int status = HttpStatus.OK.value();
        User user = userService.회원이메일찾기(email);
        System.out.println(user.getId());
        if (user.getId() != 0) {
            status = -1;
        }
        return new ResponseDto<Integer>(status, 1);
    }


//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user,HttpSession session){
//		System.out.println("user api controller : login 호출");	
//		User principal = userService.로그인(user);//principal(접근주체)
//		System.out.println("세션..?");
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//			
//		}
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
}
