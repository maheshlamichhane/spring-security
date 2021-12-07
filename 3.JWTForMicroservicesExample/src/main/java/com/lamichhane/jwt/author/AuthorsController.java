package com.lamichhane.jwt.author;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/v1/authors")
public class AuthorsController
{
	@PostMapping
	public ResponseEntity<?> addAuthor(@RequestBody Author author)
	{
		author.setAuthorId(UUID.randomUUID().toString());
		return new ResponseEntity<>(author,HttpStatus.CREATED);
	}
	
	@GetMapping(path="/{authorId}")
	public ResponseEntity<Author> getAuthorById(@PathVariable String authorId)
	{
		Author author=new Author(authorId,"Sanjay","Bgaratiya","sanjay.bharatiya@email.com");
		return new ResponseEntity<>(author,HttpStatus.OK);
	}
}
