package com.lamichhane.springsercurity.security;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter
{
	
	private static String jwtSecret=System.getenv("jwtSecret");
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager)
	{
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,FilterChain chain) throws IOException,ServletException
	{
		//Getting the header from the request
		String authHeader=request.getHeader("Authorization");
		
		//Validate the authorization header
		if(authHeader==null || authHeader.trim().length()==0 || !authHeader.startsWith("Bearer "))
		{
			chain.doFilter(request, response);
			return;
		}
		
		//Auth Header is present and is in valid format
		UsernamePasswordAuthenticationToken authenticationToken=getAuthentication(authHeader);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String authHeader)
	{
		if(authHeader !=null)
		{
			DecodedJWT decodedJWT=JWT.require(Algorithm.HMAC512(jwtSecret))
					.build().verify(authHeader.replace("Bearer ", ""));
			if(decodedJWT != null)
			{
				String userNameFromJwt=decodedJWT.getSubject();
				Set<SimpleGrantedAuthority> authorities=Stream.of(decodedJWT.getClaim("role").asString().split(","))
				.map(a -> new SimpleGrantedAuthority(a))
				.collect(Collectors.toSet());
				
			
			
			if(userNameFromJwt != null)
			{
				//UserDetails userDetails=userDetailServiceImpl.loadUserByUsername(userNameFromJwt);
				return new UsernamePasswordAuthenticationToken(userNameFromJwt,null,authorities);
			}
			}
		}
		return null;
	}
}
