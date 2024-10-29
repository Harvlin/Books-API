package com.project.springBootProject.repositories;

import com.project.springBootProject.TestDataUtility;
import com.project.springBootProject.domain.entities.AuthorEntity;
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
public class AuthorRepositoryIntegrationTest {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void TestThatAuthorCanBeCratedAndRecalled() {
        AuthorEntity authorA = TestDataUtility.createTestAuthorA();
        underTest.save(authorA);
        Optional<AuthorEntity> results = underTest.findById(authorA.getId());

        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(authorA);
    }

    @Test
    public void testThatMultipleAuthorCanBeCreatedAndRecalled() {
        AuthorEntity authorA = TestDataUtility.createTestAuthorA();
        underTest.save(authorA);
        AuthorEntity authorB = TestDataUtility.createTestAuthorB();
        underTest.save(authorB);
        AuthorEntity authorC = TestDataUtility.createTestAuthorC();
        underTest.save(authorC);

        Iterable<AuthorEntity> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(authorA, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorEntity authorA = TestDataUtility.createTestAuthorA();
        underTest.save(authorA);

        authorA.setName("UPDATED");
        underTest.save(authorA);

        Optional<AuthorEntity> result = underTest.findById(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);

    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorEntity authorA = TestDataUtility.createTestAuthorA();
        underTest.save(authorA);
        underTest.deleteById(authorA.getId());

        Optional<AuthorEntity> result = underTest.findById(authorA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorWithAgeLessThan() {
        AuthorEntity authorA = TestDataUtility.createTestAuthorA();
        underTest.save(authorA);
        AuthorEntity authorB = TestDataUtility.createTestAuthorB();
        underTest.save(authorB);
        AuthorEntity authorC = TestDataUtility.createTestAuthorC();
        underTest.save(authorC);

        Iterable<AuthorEntity> result = underTest.ageLessThan(30);
        assertThat(result).containsExactly(authorA, authorB);
    }

    @Test
    public void testThatGetAuthorWithAgeGreaterThan() {
        AuthorEntity authorA = TestDataUtility.createTestAuthorA();
        underTest.save(authorA);
        AuthorEntity authorB = TestDataUtility.createTestAuthorB();
        underTest.save(authorB);
        AuthorEntity authorC = TestDataUtility.createTestAuthorC();
        underTest.save(authorC);

        Iterable<AuthorEntity> result = underTest.findAuthorWithAgeGraterThan(30);
        assertThat(result).containsExactly(authorC);
    }
}
