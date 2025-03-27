package com.library.management.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.app.dto.Response;
import com.library.management.app.entity.Book;
import com.library.management.app.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	// find a book based on search parameters or all the books
	@GetMapping("/books")
	public Response getBook(@RequestParam(required = false) Map<String, String> qparams) {
		return bookService.searchBook(qparams);

	}

	// Add a book
	@PostMapping("/books")
	public Response addBooks(@RequestBody Book book) {
		return bookService.save(book);
	}

	// Borrow a book
	@PatchMapping("/books/{bookId}")
	public Response borrowBook(@PathVariable Long bookId, @RequestBody Book book) {
		return bookService.borrowBook(bookId, book.getBorrowedBy());
	}
}
