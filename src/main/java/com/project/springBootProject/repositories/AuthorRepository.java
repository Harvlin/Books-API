package com.project.springBootProject.repositories;

import com.project.springBootProject.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> ageLessThan(int age);

    @Query("select a from Author a where a.age > ?1")
    Iterable<Author> findAuthorWithAgeGraterThan(int age);
}
