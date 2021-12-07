package com.lamichhane.springsercurity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserDetailServiceImpl userDetailsService;
	private BooksWsAuthenticationEntryPoint authenticationEntryPoint;
	
	
	public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailServiceImpl userDetailsService,
			BooksWsAuthenticationEntryPoint authenticationEntryPoint) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated()
		.and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
}
