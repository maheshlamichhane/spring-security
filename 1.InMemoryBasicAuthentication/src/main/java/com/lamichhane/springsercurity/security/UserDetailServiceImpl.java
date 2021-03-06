package com.lamichhane.springsercurity.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lamichhane.springsercurity.user.User;
import com.lamichhane.springsercurity.user.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	private UserService userService;
	public UserDetailServiceImpl(UserService userService)
	{
		this.userService=userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	   User user=userService.getUserByUsername(username);
	   if(user != null)
	   {
		   return user;
	   }
	   else {
		   throw new UsernameNotFoundException(username+ "does not exist");
	   }
	}

}
