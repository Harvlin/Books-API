package com.project.springBootProject.services.impl;

import com.project.springBootProject.domain.entities.BookEntity;
import com.project.springBootProject.repositories.BookRepository;
import com.project.springBootProject.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
}
