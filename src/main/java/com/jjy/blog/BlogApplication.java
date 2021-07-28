package com.jjy.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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
    }

}
