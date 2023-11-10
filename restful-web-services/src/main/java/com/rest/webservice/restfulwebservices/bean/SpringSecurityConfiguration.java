package com.rest.webservice.restfulwebservices.bean;

//import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//All request to be authenticated (not through login but through pop up)
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.httpBasic(Customizer.withDefaults());


        //Disable CSRF(Cross Site Request Forgery, auto-enabled in spring security)
        http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
}
