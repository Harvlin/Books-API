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

    @Test
    public void testThatMultipleBookCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtility.createTestAuthorA();

        Book bookA = TestDataUtility.createTestBookA(authorA);
        underTest.save(bookA);

        Book bookB = TestDataUtility.createTestBookB(authorA);
        underTest.save(bookB);

        Book bookC = TestDataUtility.createTestBookC(authorA);
        underTest.save(bookC);

        Iterable<Book> results = underTest.findAll();
        assertThat(results).hasSize(3).containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        Author authorA = TestDataUtility.createTestAuthorA();
        Book bookA = TestDataUtility.createTestBookA(authorA);

        underTest.save(bookA);
        bookA.setTitle("UPDATED");
        underTest.save(bookA);

        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Author authorA = TestDataUtility.createTestAuthorA();

        Book bookA = TestDataUtility.createTestBookA(authorA);
        underTest.save(bookA);
        underTest.deleteById(bookA.getIsbn());

        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}