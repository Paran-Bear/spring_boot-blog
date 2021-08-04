package com.jjy.blog.controller;

import java.util.UUID;

import com.jjy.blog.dto.ResponseDto;
import com.jjy.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjy.blog.config.auth.PrincipalDetail;
import com.jjy.blog.model.KakaoProfile;
import com.jjy.blog.model.OAuthToken;
import com.jjy.blog.model.User;
import com.jjy.blog.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

//미인증 사용자들의 출입 경로 -->/auth/* 허용.
// 그냥 주소가 / 인 경로를 허용 --> index.jsp
//static이하 파일들 (/js/*, css/*, image/* 등등
//인증이 필요없는 페이지는 /auth
@Controller
public class UserController {

	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private BoardService boardService;




	@GetMapping({"/",""})
	public String index(HttpServletResponse response, Model model,@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Cookie cookie =new Cookie("view",null); 	//view라는 이름의 쿠키 생성
		cookie.setComment("게시글 조회 확인");		//해당 쿠키가 어떤 용도인지 커멘트
		cookie.setMaxAge(60*60*24*365);			//해당 쿠키의 유효시간을 설정 (초 기준)
		response.addCookie(cookie);
		model.addAttribute("boards",boardService.글목록(pageable));
		return "index";

	}
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {

		return "user/joinForm";

	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {

		return "user/loginForm";

	}

	@GetMapping("/user/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {

		return "user/updateForm";

	}

	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {//Data를 리턴해주는 컨트롤러 함수가 된다.
		
		//POST방식으로 Key=value 데이터를 카카오에 요청
		//<a>태그를 통한 요청은 다 GET
		
		//POST요청 라이브러리 --> Retrofit(안드로이드에서 많이씀), OkHttp, RestTemplate 등등
		RestTemplate rt=new RestTemplate();
		
		//HttpHeader오브젝트 생성
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody오브젝트 생성
		MultiValueMap<String, String> params=new LinkedMultiValueMap<>();
		params.add("grant_type","authorization_code");
		params.add("client_id","c7041e2ccdc7d398c97df1eeb2a0e598");
		params.add("redirect_uri","http://www.parangom.xyz/auth/kakao/callback");
		params.add("code", code);
		//위의 값들은 사실 변수를 만들어 활용하는게 좋지만, 실습과 이해를 위해 생코딩
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest=
				new HttpEntity<>(params,headers);
		//Body와 Header값을 가진 Entity가 됨.
		
		
		//Http요청 --Post방식 --Response로 변수 응답 받음.
		ResponseEntity<String> response=rt.exchange(
				"https://kauth.kakao.com/oauth/token",//토큰 발급 요청 주소
				HttpMethod.POST,//요청 메소드
				kakaoTokenRequest,//HTTP BODY,HEADER 데이터
				String.class//응답 받을 타입.
				);
		
		
		//Gson, Json Simple, ObjectMapper등 라이브러리 사용
		OAuthToken oauthToken=null;
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			oauthToken=objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {//JSON데이터와 변수명이 다르면 파싱 오류가 나옴.
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
				
		
		//------------------------------------------
		RestTemplate rt2=new RestTemplate();
		
		//HttpHeader오브젝트 생성
		HttpHeaders headers2=new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2=
				new HttpEntity<>(headers2);
		//Body와 Header값을 가진 Entity가 됨.
		
		
		//Http요청 --Post방식 --Response로 변수 응답 받음.
		ResponseEntity<String> response2=rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",//토큰 발급 요청 주소
				HttpMethod.POST,//요청 메소드
				kakaoProfileRequest2,//HTTP BODY,HEADER 데이터
				String.class//응답 받을 타입.
				);
		
		ObjectMapper objectMapper2=new ObjectMapper();
		KakaoProfile kakaoProfile=null;
		
		try {
			kakaoProfile=objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {//JSON데이터와 변수명이 다르면 파싱 오류가 나옴.
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//User 오브젝트 --> username, password, email, role
		//UUID란-->중복 되지 않는 어떤 특정 값을 만드는 알고리즘. 로그인 시 계속 바뀌는값을 쓰면 안됨.
		
		
		
		//추가적인 회원 정보가 필요시 제작
		User kakaoUser=User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.nickname(kakaoProfile.getProperties().getNickname())
				.oauth("kakao")
				.build();
		
		//가입자 혹은 비가입자 확인을 위해 분기를 해서 확인
		User originUser=userService.회원찾기(kakaoUser.getUsername());
		
		
		
		if(originUser.getUsername()==null) {
			if(kakaoUser.getEmail()==null || kakaoUser.getEmail().equals(""))
			{
				kakaoUser.setEmail("kakao_user_NonEmail");
			}
			userService.회원가입(kakaoUser);
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			return "user/updateFormKakao--";
			//비가입자-->회원 가입 후 로그인처리
		}//가입자 --> 바로 로그인 처리
		
		//로그인처리
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
		
	}

}
