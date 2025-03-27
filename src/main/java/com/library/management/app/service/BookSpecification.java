package com.library.management.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.library.management.app.entity.Book;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BookSpecification implements Specification<Book> {

	private String title;
	private String genre;
	private String author;
	private Boolean isBorrowed;
	private String borrowedBy;

	public BookSpecification(String title, String genre, String author, String borrowed, String borrowedBy) {
		this.title = title;
		this.genre = genre;
		this.author = author;
		if (borrowed != null) {
			if (borrowed.equals("yes") || borrowed.equals("true")) {
				isBorrowed = true;
			} else {
				isBorrowed = false;
			}
		}
		this.borrowedBy = borrowedBy;
	}

	@Override
	public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();

		if (title != null) {
			predicates.add(
					criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
		}

		if (genre != null) {
			predicates.add(
					criteriaBuilder.like(criteriaBuilder.lower(root.get("genre")), "%" + genre.toLowerCase() + "%"));
		}

		if (author != null) {
			predicates.add(
					criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
		}

		if (isBorrowed != null) {
			predicates.add(criteriaBuilder.equal(root.get("isBorrowed"), isBorrowed));
		}

		if (borrowedBy != null) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("borrowedBy")),
					"%" + borrowedBy.toLowerCase() + "%"));
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
}
