package com.library.management.app.dto;

import org.springframework.stereotype.Component;

import com.library.management.app.entity.Book;

@Component
public class BookEntityToDtoConvertor {
	public BookDto convertEntityToDto(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setBookId(book.getBookId());
		bookDto.setAuthor(book.getAuthor());
		bookDto.setIsBorrowed(book.getIsBorrowed());
		bookDto.setGenre(book.getGenre());
		bookDto.setBorrowedBy(book.getBorrowedBy());
		bookDto.setTitle(book.getTitle());
		return bookDto;
	}
	public Book convertDtoToEntity(BookDto bookDto) {
		Book book = new Book();
		book.setAuthor(bookDto.getAuthor());
		book.setIsBorrowed(bookDto.getIsBorrowed());
		book.setGenre(bookDto.getGenre());
		book.setBorrowedBy(bookDto.getBorrowedBy());
		book.setTitle(bookDto.getTitle());
		return book;
	}
	
}
