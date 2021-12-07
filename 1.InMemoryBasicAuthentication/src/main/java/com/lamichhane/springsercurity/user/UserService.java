package com.lamichhane.springsercurity.user;

import org.springframework.stereotype.Service;

@Service
public class UserService 
{
	private User user=null;
	public UserService() {
		user=new User("myusername","$2y$12$YmstRlO5v7QInu9gxviKhuFDkC2R.EH42k.Skhf7/Shc.yEhXXK3i",true);
	}
	
	public User getUserByUsername(String username) {
		if(user.getUsername().equals(username))
		{
			return user;
		}
		else
		{
			return null;
		}
	}
}
