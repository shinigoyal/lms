package com.library.management.app.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.app.dto.BookDto;
import com.library.management.app.dto.BookEntityToDtoConvertor;
import com.library.management.app.dto.Response;
import com.library.management.app.entity.Book;
import com.library.management.app.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookEntityToDtoConvertor dtoConvertor;

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}

	public Response save(Book book) {
		Response response = new Response();

		try {
			Book bookObj = bookRepository.save(book);
			List<BookDto> bookList = new ArrayList<>();
			bookList.add(dtoConvertor.convertEntityToDto(bookObj));
			response.setBooks(bookList);
			response.setCount(1);
			response.setSuccess(true);
			response.setMessage(MessageFormat.format("Book with title {0} and ID {1} added successfully",
					bookObj.getTitle(), bookObj.getBookId()));
		} catch (Exception ex) {
			response.setSuccess(false);
			response.setMessage(MessageFormat.format("Error {0} while saving book with title {1} ",
					ex.getLocalizedMessage(), book.getTitle()));
		}

		return response;
	}

	public Response borrowBook(Long bookId, String borrower) {
		Book book = findById(bookId);
		Response response;
		if (book != null && !book.getIsBorrowed()) {
			book.setIsBorrowed(true);
			book.setBorrowedBy(borrower);
			response = save(book);
			if (response.isSuccess()) {
				response.setMessage(MessageFormat.format("Book with ID {0} borrowed successfully", bookId));
			}
		} else {
			response = new Response();
			response.setMessage("Book is either not found or already borrowed by somebody else");
			response.setSuccess(true);
		}
		return response;
	}

	public Response searchBook(Map<String, String> qparams) {
		List<Book> books = new ArrayList<>();

		String author = qparams.get("author");
		String title = qparams.get("title");
		String genre = qparams.get("genre");
		String borrowed = qparams.get("borrowed");
		String borrowedBy = qparams.get("borrowedBy");

		BookSpecification spec = new BookSpecification(title, genre, author, borrowed, borrowedBy);

		books = bookRepository.findAll(spec);

		return getResponse(books);
	}

	public Response getResponse(List<Book> books) {
		List<BookDto> bookDtos = books.stream().map(e -> dtoConvertor.convertEntityToDto(e))
				.collect(Collectors.toList());

		Response response = new Response();
		response.setBooks(bookDtos);
		response.setCount(bookDtos.size());
		response.setSuccess(true);
		response.setMessage("Search completed");

		return response;
	}
}