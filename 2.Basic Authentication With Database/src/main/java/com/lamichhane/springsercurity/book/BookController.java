package com.lamichhane.springsercurity.book;

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
@RequestMapping(path="/v1/books")
public class BookController
{
	@GetMapping(path="/{bookId}")
	private ResponseEntity<Book> getBookById(@PathVariable String bookId) 
	{
		Book book=new Book(bookId,UUID.randomUUID().toString(),"API Security",
		"SKB Publisher","01-02-2010");
		return new ResponseEntity<>(book,HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<?> addBook(@RequestBody Book book) 
	{
		book.setBookId(UUID.randomUUID().toString());
		return new ResponseEntity<>(book,HttpStatus.CREATED);
	}
}
