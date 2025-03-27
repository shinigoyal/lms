package com.library.management.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

//custom class for response of rest api endpoints
@JsonInclude(Include.NON_NULL)
@Data
public class Response {

	private int count;
	private String message;
	private boolean success;
	private List<BookDto> books;
}
