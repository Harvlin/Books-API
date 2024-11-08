package com.project.springBootProject.services.impl;

import com.project.springBootProject.domain.dto.AuthorDto;
import com.project.springBootProject.domain.dto.BookDto;
import com.project.springBootProject.domain.entities.AuthorEntity;
import com.project.springBootProject.domain.entities.BookEntity;
import com.project.springBootProject.repositories.BookRepository;
import com.project.springBootProject.services.AuthorService;
import com.project.springBootProject.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }


    @Override
    public BookEntity createUpdateBook(String isbn, BookEntity book) {
        AuthorEntity author = authorService.save(book.getAuthor());
        book.setAuthor(author);
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    /*
    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
     */

    @Override
    public Page<BookEntity> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExist(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);

        return bookRepository.findById(isbn).map(existingBook -> {
            Optional.ofNullable(bookEntity.getIsbn()).ifPresent(existingBook::setIsbn);
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book Does Not Exist"));
    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
