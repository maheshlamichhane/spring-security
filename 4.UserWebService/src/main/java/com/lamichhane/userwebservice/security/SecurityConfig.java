package com.lamichhane.userwebservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserDetailServiceImpl userDetailsService;
	
	
	
	public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailServiceImpl userDetailsService) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable().authorizeRequests()
		//.antMatchers("/v1/books/{boodId}").hasAnyAuthority("USER","ADMIN")
		//.antMatchers("/v1/books").hasAuthority("ADMIN")
		//spring security automatically prepends role_ before any role
		.antMatchers("/v1/authors/{authorId}").access("hasRole('USER') and hasAuthority('GET_BOOK')")
		.antMatchers("/v1/authors").access("hasRole('ADMIN') and hasAuthority('CREATE_BOOK')")
		.anyRequest().authenticated().and().addFilter(new JwtAuthenticationFilter(authenticationManager()))
		//.and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
}
