package com.project.springBootProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springBootProject.TestDataUtility;
import com.project.springBootProject.domain.dto.AuthorDto;
import com.project.springBootProject.domain.dto.BookDto;
import com.project.springBootProject.domain.entities.BookEntity;
import com.project.springBootProject.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private BookService bookService;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    @Test
    public void TestThatCreateBookSuccessfullyReturnHttp201Created() throws Exception {
        AuthorDto authorDto = TestDataUtility.createTestAuthorDtoA();
        BookDto bookDto = TestDataUtility.createTestBookDtoA(authorDto);
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void TestThatCreateBookSuccessfullyReturnSavedBook() throws Exception {
        AuthorDto authorDto = TestDataUtility.createTestAuthorDtoA();
        BookDto bookDto = TestDataUtility.createTestBookDtoA(authorDto);
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("1234567890")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("The Beginning After The End")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(authorDto)
        );
    }

    @Test
    public void TestThatListBookReturnHttpsStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void TestThatListBookReturnedListOfBook() throws Exception {
        BookEntity bookEntityA = TestDataUtility.createTestBookA(null);
        bookService.createBook(bookEntityA.getIsbn(), bookEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("The Beginning After The End")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("1234567890")
        );
    }

    @Test
    public void TestThatGetBookReturnHttpsStatus200() throws Exception {
        BookEntity bookEntityA = TestDataUtility.createTestBookA(null);
        bookService.createBook(bookEntityA.getIsbn(), bookEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/1234567890")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void TestThatGetBookReturnBook() throws Exception {
        BookEntity bookEntityA = TestDataUtility.createTestBookA(null);
        bookService.createBook(bookEntityA.getIsbn(), bookEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/1234567890")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("The Beginning After The End")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("1234567890")
        );
    }
}
