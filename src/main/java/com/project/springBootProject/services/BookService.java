package com.project.springBootProject.services;

import com.project.springBootProject.domain.entities.BookEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);
}
