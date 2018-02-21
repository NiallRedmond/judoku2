package com.example.judoku;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.judoku.repository.UserRepository;
import com.example.judoku.dto.UserRegistrationDto;
import com.example.judoku.model.Role;
import com.example.judoku.model.User;

//import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
@SpringBootApplication
@EnableJpaAuditing
public class JudokuApplication {

	public static void main(String[] args) {
		SpringApplication.run(JudokuApplication.class, args);
	}		
}
**/

@SpringBootApplication
@EnableJpaAuditing
public class JudokuApplication extends SpringBootServletInitializer {
	

	    
	    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JudokuApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(JudokuApplication.class, args);
        
        
     
        
    }


}