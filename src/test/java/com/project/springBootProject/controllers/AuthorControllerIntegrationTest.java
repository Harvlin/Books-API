package com.project.springBootProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springBootProject.TestDataUtility;
import com.project.springBootProject.domain.dto.AuthorDto;
import com.project.springBootProject.domain.entities.AuthorEntity;
import com.project.springBootProject.services.AuthorService;
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
public class AuthorControllerIntegrationTest {
    private AuthorService authorService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.authorService = authorService;
    }

    @Test
    public void TestThatCreateAuthorSuccessfullyReturnHttp201Created() throws Exception{
        AuthorDto authorDto = TestDataUtility.createTestAuthorDtoA();
        authorDto.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void TestThatCreateAuthorSuccessfullyReturnSavedAuthor() throws Exception{
        AuthorDto authorDto = TestDataUtility.createTestAuthorDtoA();
        authorDto.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Harvlin")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("15")
        );
    }

    @Test
    public void TestThatListAuthorReturnHttpStatus200() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void TestThatListAuthorReturnListOfAuthors() throws Exception {
        AuthorEntity authorEntityA = TestDataUtility.createTestAuthorA();
        authorService.save(authorEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Harvlin")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value("15")
        );
    }

    @Test
    public void TestThatGetAuthorReturnHttpStatus200() throws Exception{
        AuthorEntity authorEntityA = TestDataUtility.createTestAuthorA();
        authorService.save(authorEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void TestThatGetAuthorReturnAuthor() throws Exception{
        AuthorEntity authorEntityA = TestDataUtility.createTestAuthorA();
        authorService.save(authorEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Harvlin")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("15")
        );
    }

    @Test
    public void TestThatFullUpdateAuthorReturnHttpStatus404WhenNoAuthorExist() throws Exception{
        AuthorDto authorDto = TestDataUtility.createTestAuthorDtoA();
        String authorDtoJson = objectMapper.writeValueAsString(authorDto);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void TestThatFullUpdateUpdatesExistingAuthor() throws Exception {
        AuthorEntity authorEntityA = TestDataUtility.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(authorEntityA);

        AuthorEntity authorDto  = TestDataUtility.createTestAuthorB();
        authorDto.setId(savedAuthorEntity.getId());

        String authorDtoUpdateJson = objectMapper.writeValueAsString(authorDto);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoUpdateJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthorEntity.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(savedAuthorEntity.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(savedAuthorEntity.getAge())
        );
    }

    @Test
    public void TestThatPartialUpdateExistingAuthorReturnHttpStatus200Ok() throws  Exception{
        AuthorEntity authorEntityA = TestDataUtility.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(authorEntityA);

        AuthorDto authorDto = TestDataUtility.createTestAuthorDtoA();
        authorDto.setName("UPDATED");
        String authorDtoJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void TestThatPartialUpdateUpdatesExistingAuthor() throws Exception {
        AuthorEntity authorEntityA = TestDataUtility.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(authorEntityA);

        AuthorEntity authorDto  = TestDataUtility.createTestAuthorB();
        authorDto.setName("UPDATED");
        String authorDtoUpdateJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoUpdateJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthorEntity.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("UPDATED")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge())
        );
    }

    @Test
    public void TestThatDeleteAuthorReturnHttpStatus204() throws Exception{
        AuthorEntity authorEntityA = TestDataUtility.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(authorEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
