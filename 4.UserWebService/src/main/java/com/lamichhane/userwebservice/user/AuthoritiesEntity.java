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
@Table(name="authorities")
public class AuthoritiesEntity
{
	/* One User Can Have Multiple Authorities there is one to many mapping between
	 * the user table and the authorities table
	 *  */
	@Column(name="authority_Id")
	@Id
	private Integer authorityId;
	
	@Column(name="authority")
	private String authority;
	
	@Column(name="role")
	private String role;
	

	
	

	public AuthoritiesEntity() {
	}







	public AuthoritiesEntity(Integer authorityId, String authority, String role) {
		this.authorityId = authorityId;
		this.authority = authority;
		this.role = role;

	}







	@Override
	public String toString() {
		return "AuthoritiesEntity [authorityId=" + authorityId + ", authority=" + authority + "]";
	}



	public Integer getAuthorityId() {
		return authorityId;
	}



	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}



	public String getAuthority() {
		return authority;
	}



	public void setAuthority(String authority) {
		this.authority = authority;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	


	
	
}
