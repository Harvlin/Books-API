package com.project.springBootProject.repositories;

import com.project.springBootProject.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Iterable<AuthorEntity> ageLessThan(int age);

    @Query("select a from AuthorEntity a where a.age > ?1")
    Iterable<AuthorEntity> findAuthorWithAgeGraterThan(int age);
}
