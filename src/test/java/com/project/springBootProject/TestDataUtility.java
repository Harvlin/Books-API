package com.project.springBootProject;

import com.project.springBootProject.domain.dto.AuthorDto;
import com.project.springBootProject.domain.dto.BookDto;
import com.project.springBootProject.domain.entities.AuthorEntity;
import com.project.springBootProject.domain.entities.BookEntity;

public class TestDataUtility {
    private TestDataUtility() {

    }

    // ENTITY
    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Harvlin")
                .age(15)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Max")
                .age(25)
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .id(3L)
                .name("John")
                .age(50)
                .build();
    }
    public static BookEntity createTestBookA(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("1234567890")
                .title("The Beginning After The End")
                .author(author)
                .build();
    }

    public static BookEntity createTestBookB(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("0987654321")
                .title("ORV")
                .author(author)
                .build();
    }

    public static BookEntity createTestBookC(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("Solo Leveling")
                .title("DEF")
                .author(author)
                .build();
    }



    // DTO
    public static AuthorDto createTestAuthorDtoA() {
        return AuthorDto.builder()
                .id(1L)
                .name("Harvlin")
                .age(15)
                .build();
    }

    public static AuthorDto createTestAuthorDtoB() {
        return AuthorDto.builder()
                .id(2L)
                .name("Max")
                .age(25)
                .build();
    }

    public static AuthorDto createTestAuthorDtoC() {
        return AuthorDto.builder()
                .id(3L)
                .name("John")
                .age(50)
                .build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto authorDto) {
        return BookDto.builder()
                .isbn("1234567890")
                .title("The Beginning After The End")
                .author(authorDto)
                .build();
    }

    public static BookDto createTestBookDtoB(final AuthorDto authorDto) {
        return BookDto.builder()
                .isbn("0987654321")
                .title("ORV")
                .author(authorDto)
                .build();
    }

    public static BookDto createTestBookDtoC(final AuthorDto authorDto) {
        return BookDto.builder()
                .isbn("Solo Leveling")
                .title("DEF")
                .author(authorDto)
                .build();
    }
}
