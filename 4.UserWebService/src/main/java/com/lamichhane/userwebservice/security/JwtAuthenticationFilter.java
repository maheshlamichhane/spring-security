package com.lamichhane.userwebservice.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamichhane.userwebservice.user.User;



public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
	private AuthenticationManager authenticationManager;
	private static String jwtSecret=System.getenv("jwtSecret");
	

	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager)
	{
		this.authenticationManager=	authenticationManager;
	}
	
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse
			 respone ) throws AuthenticationException
	{
		User user=null;
		try
		{
			user=new ObjectMapper().readValue(request.getInputStream(),User.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
					user.getPassword(),new ArrayList<>()));
		}
		catch(BadCredentialsException bce)
		{
			throw bce;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response,FilterChain chain,Authentication auth) throws IOException,ServletException
	{
		User user=(User) auth.getPrincipal();
		String token=JWT.create().withSubject(user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis()+1800000))
				.withClaim("role", user.getAuthorities().stream()
						.map(o -> ((GrantedAuthority) o).getAuthority())
						 .collect(Collectors.joining(",")))
				.sign(HMAC512(jwtSecret.getBytes()));
		response.addHeader("Authorization", "Bearer "+token);
	}
}
