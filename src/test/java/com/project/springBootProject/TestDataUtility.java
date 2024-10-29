package com.project.springBootProject;

import com.project.springBootProject.domain.entities.AuthorEntity;
import com.project.springBootProject.domain.entities.BookEntity;

public class TestDataUtility {
    private TestDataUtility() {

    }

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
                .title("The beginning after the end")
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
}
