package com.lamichhane.userwebservice.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRoleEntity
{
	
	@Column(name="user_role_id")
	@Id
	private Integer userRoleId;
	
	@Column(name="role")
	private String role;
	
	public UserRoleEntity() {
	}
	
	
	
	
	
	public UserRoleEntity(Integer userRoleId, String role) {
		this.userRoleId = userRoleId;
		this.role = role;
	}





	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="username",nullable=false)
	private UserEntity userEntity;
	
	
	
	
	

	



	public Integer getUserRoleId() {
		return userRoleId;
	}





	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}





	public String getRole() {
		return role;
	}





	public void setRole(String role) {
		this.role = role;
	}





	public UserEntity getUserEntity() {
		return userEntity;
	}



	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	
	
	


	
	
}
