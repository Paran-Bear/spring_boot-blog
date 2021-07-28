package com.jjy.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jjy.blog.config.auth.PrincipalDetailService;

//빈등록:스프링 컨테이너에서 객체를 관리 할 수 있게 하는것
@Configuration // 빈등록(IoC관리)
@EnableWebSecurity // 시큐리티 필터가 등록됨. 이하 필터 설정은 아래 클래스에서함.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크함.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	@Bean  //어디에서도 DI하여 사용할 수 있음.
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Bean // IoC됨.-->리턴값을 스프링이 관리함. 필요할 때 마다 가져와서 사용.
	public BCryptPasswordEncoder encodePWD() {

		return new BCryptPasswordEncoder();
	}

	//시큐리티가 대신 로그인시 패스워드를 가로챔.
	//해당 패스워드가 뭘로 해쉬가 되었는지 알아야
	//같은 해쉬로 암호화 하여 DB에 있는 해쉬랑 비교를 할 수 있음.
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()// csrf 토큰 비 활성화.(테스트 할 때)
				.authorizeRequests()
				.antMatchers("/","/actuator/**","/profile", "/auth/**", "/js/**", "/css/**", "/image/**","/board/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")
				.defaultSuccessUrl("/");
		//실패시 요청도 있음 --> .failur("")
		//스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서
		//대신 로그인함.

	}

}
