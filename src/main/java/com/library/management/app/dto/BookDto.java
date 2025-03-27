package com.library.management.app.dto;

import lombok.Data;

@Data
public class BookDto {
	private Long bookId;
	private String title;
	private String author;
	private String genre;
	private Boolean isBorrowed;
	private String borrowedBy;

}
