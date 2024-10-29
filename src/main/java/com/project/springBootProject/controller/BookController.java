package com.project.springBootProject.controller;

import com.project.springBootProject.domain.dto.BookDto;
import com.project.springBootProject.domain.entities.BookEntity;
import com.project.springBootProject.mappers.Mapper;
import com.project.springBootProject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    private Mapper<BookEntity, BookDto> bookMapper;
    private BookService bookService;

    @Autowired
    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService  = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);

        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
    }
}
