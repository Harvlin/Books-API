package com.project.springBootProject.repositories;

import com.project.springBootProject.TestDataUtility;
import com.project.springBootProject.domain.entities.AuthorEntity;
import com.project.springBootProject.domain.entities.BookEntity;
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
        AuthorEntity authorA = TestDataUtility.createTestAuthorA();
        BookEntity bookA = TestDataUtility.createTestBookA(authorA);

        underTest.save(bookA);
        Optional<BookEntity> results = underTest.findById(bookA.getIsbn());

        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(bookA);
    }

    @Test
    public void TestThatMultipleBookCanBeCreatedAndRecalled() {
        AuthorEntity authorA = TestDataUtility.createTestAuthorA();
        BookEntity bookA = TestDataUtility.createTestBookA(authorA);
        underTest.save(bookA);
        BookEntity bookB = TestDataUtility.createTestBookB(authorA);
        underTest.save(bookB);
        BookEntity bookC = TestDataUtility.createTestBookC(authorA);
        underTest.save(bookC);

        Iterable<BookEntity> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void TestThatBookCanBeUpdated() {
        AuthorEntity authorA = TestDataUtility.createTestAuthorA();
        BookEntity bookA = TestDataUtility.createTestBookA(authorA);
        underTest.save(bookA);

        bookA.setTitle("UPDATED");
        underTest.save(bookA);

        Optional<BookEntity> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void TestThatBookCanBeDeleted() {
        AuthorEntity authorA = TestDataUtility.createTestAuthorA();
        BookEntity bookA = TestDataUtility.createTestBookA(authorA);

        underTest.save(bookA);
        underTest.deleteById(bookA.getIsbn());

        Optional<BookEntity> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}