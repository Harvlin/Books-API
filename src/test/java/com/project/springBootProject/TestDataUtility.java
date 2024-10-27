package com.project.springBootProject;

import com.project.springBootProject.domain.Author;
import com.project.springBootProject.domain.Book;

public class TestDataUtility {
    private TestDataUtility() {

    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Harvlin")
                .age(15)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Max")
                .age(25)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("John")
                .age(50)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("1234567890")
                .title("The beginning after the end")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("0987654321")
                .title("ORV")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("Solo Leveling")
                .title("DEF")
                .author(author)
                .build();
    }
}
