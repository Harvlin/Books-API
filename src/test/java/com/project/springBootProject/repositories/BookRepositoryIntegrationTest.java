package com.project.springBootProject.repositories;

import com.project.springBootProject.TestDataUtility;
import com.project.springBootProject.domain.Author;
import com.project.springBootProject.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {
    private BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTest(BookRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void TestThatBookCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtility.createTestAuthorA();
        Book book = TestDataUtility.createTestBookA(authorA);
        underTest.save(book);

        Optional<Book> results = underTest.findById(book.getIsbn());
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(book);
    }
/*
    @Test
    public void testThatMultipleBookCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtility.createTestAuthorA();
        authorDao.create(authorA);

        Book bookA = TestDataUtility.createTestBookA();
        bookA.setAuthorId(authorA.getId());
        underTest.create(bookA);

        Book bookB = TestDataUtility.createTestBookB();
        bookB.setAuthorId(authorA.getId());
        underTest.create(bookB);

        Book bookC = TestDataUtility.createTestBookC();
        bookC.setAuthorId(authorA.getId());
        underTest.create(bookC);

        List<Book> results = underTest.findMany();
        assertThat(results).hasSize(3).containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Author authorA = TestDataUtility.createTestAuthorA();
        authorDao.create(authorA);

        Book bookA = TestDataUtility.createTestBookA();
        bookA.setAuthorId(authorA.getId());
        underTest.create(bookA);

        underTest.delete(bookA.getIsbn());
        Optional<Book> result = underTest.findOne(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
 */
}