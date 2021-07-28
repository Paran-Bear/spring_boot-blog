package com.jjy.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jjy.blog.model.User;

import lombok.Data;
import lombok.Getter;

@Data
public class PrincipalDetail implements UserDetails{
	
	
	private User user; //콤포지션(객체를 품고 있음)extends(상속)와는 다름.

	public PrincipalDetail(User user) {
		this.user=user;
	}
	
	
//스프링 시큐리티가 로그인 요청을 가로채서 로그인 진행 후 완료가 되면 UserDetail타입의 오브젝트를
//스프링 시큐리티의 고유한 세션 저장소에 저장을 함.
	//-->UserDetails타입의 PrincipalDetail이 저장됨.
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
		//계정이 만료되었는지를 리턴함. true-->만료되지 않음.
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
		//계정이 잠겨있는지 아닌지를 리턴함.true --> 잠기지 않음 
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
		//비밀번호 만료. true-->만료X
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
		//계정 활성화 관련. true-->활성화
	}

	//계정이 가지고 있는 권한을 리턴함(권한이 많으면 for문을 돌리자)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		Collection<GrantedAuthority> collectors=new ArrayList<>();
		
		collectors.add(()->{return "ROLE_"+user.getRole();});
		return collectors;
	}
	
}
