package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {
	//configuring filter to spring security
	//Spring security will authenticate all request which is not required all the time.
	//SecurityFilterChain is used to Customize the security.
	//To Customize the SecurityFilterChain we use HttpSecurity.
	//disabling CSRF
	//Stteless Rest API
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		//1: Response to preflight request does not pass access control check
		//2: basic auth
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest()
				.authenticated()
				);
		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(
				session -> session.sessionCreationPolicy
				(SessionCreationPolicy.STATELESS)
				);
		http.csrf().disable();
		return http.build();
	}
}
