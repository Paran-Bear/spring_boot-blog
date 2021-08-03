package com.jjy.blog;

import com.nhncorp.lucy.security.xss.XssPreventer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@EnableJpaAuditing
@SpringBootApplication
public class BlogApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:/application.yml,"
            + "/app/config/Blog_Project/real-application.yml";

    public static void main(String[] args) {
    	
        new SpringApplicationBuilder(BlogApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
        XssPreventer.escape("<script></>");
    }
    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> getFilterRegistrationBean(){
        FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssEscapeServletFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}



