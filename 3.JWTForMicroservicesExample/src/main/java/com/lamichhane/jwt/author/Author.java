package com.lamichhane.jwt.author;

public class Author
{
	private String authorId;
	private String firstName;
	private String lastName;
	private String email;
	
	
	public Author() {
	}


	public Author(String authorId, String firstName, String lastName, String email) {
		this.authorId = authorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + "]";
	}


	public String getAuthorId() {
		return authorId;
	}


	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
}
