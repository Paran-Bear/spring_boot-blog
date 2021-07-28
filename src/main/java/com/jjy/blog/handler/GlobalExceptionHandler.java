package com.jjy.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.jjy.blog.dto.ResponseDto;

@ControllerAdvice//모든 클래스에서 Exception발생시 이 어노테이션으로 들어옴.
@RestController
public class GlobalExceptionHandler {
	//모든 exception을 사용 해도 된다.
	@ExceptionHandler(value = Exception.class)
	public ResponseDto<String> handleArgumentException(Exception e){
		
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		//500에러 응답.
		
	}

}
