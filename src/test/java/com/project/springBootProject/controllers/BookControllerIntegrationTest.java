package com.project.springBootProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springBootProject.TestDataUtility;
import com.project.springBootProject.domain.entities.AuthorEntity;
import com.project.springBootProject.domain.entities.BookEntity;
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

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void TestThatCreateBookSuccessfullyReturnHttp201Created() throws Exception {
        AuthorEntity authorEntityA = TestDataUtility.createTestAuthorA();
        BookEntity bookEntityA = TestDataUtility.createTestBookA(authorEntityA);
        String bookJson = objectMapper.writeValueAsString(bookEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookEntityA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void TestThatCreateBookSuccessfullyReturnSavedBook() throws Exception{
        AuthorEntity authorEntityA = TestDataUtility.createTestAuthorA();
        BookEntity bookEntityA = TestDataUtility.createTestBookA(authorEntityA);
        String bookJson = objectMapper.writeValueAsString(bookEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookEntityA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("1234567890")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("The Beginning After The End")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(authorEntityA)
        );
    }
}
