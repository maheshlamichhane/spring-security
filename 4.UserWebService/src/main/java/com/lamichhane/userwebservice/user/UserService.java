package com.lamichhane.userwebservice.user;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
	private UserRepository userRepository;
	private AuthoritiesRepository authoritiesRepository;
	
	public UserService(UserRepository userRepository,AuthoritiesRepository authoritiesRepository)
	{
		this.userRepository=userRepository;
		this.authoritiesRepository=authoritiesRepository;
	}
	
	public User getUserByUsername(String username)
	{
		UserEntity userEntity=userRepository.findByUsername(username);
		if(userEntity != null)
		{
			return createUserFromUserEntity(userEntity);
		}
		else {
			return null;
		}
	}

	private User createUserFromUserEntity(UserEntity ue)
	{
		
		User user= new User(ue.getUsername(),ue.getPassword(),ue.isEnabled());
		//Fetch Authorities From authorities table
		Stream<Stream<String>> streamStreamAuths=ue.getUserRoleEntities().stream()
		.map(ur -> {
			Set<AuthoritiesEntity> authoritiesEntities=authoritiesRepository.findByRole(ur.getRole());
			return authoritiesEntities.stream().map(ae -> ae.getAuthority());
		});
		// Flatten the stream of stream to get the set of authorities
			Set<String> authorities=streamStreamAuths.flatMap(authStream -> authStream)
			.collect(Collectors.toSet());
			
		//Now add the Role(from the User_Role table) as authorities beacouse userDetails does not support adding role separately
		//support adding Role separately as it does not have any setRole(String role)
	    ue.getUserRoleEntities().stream().forEach(ur -> authorities.add(ur.getRole()));
	    
	    //set all authorities for the user
	    user.setAuthorities(authorities.stream()
	    		.map(a -> new SimpleGrantedAuthority(a))
	    		.collect(Collectors.toSet()));
			return user;
	}
}
