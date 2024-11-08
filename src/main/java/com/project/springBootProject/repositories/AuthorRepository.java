package com.project.springBootProject.repositories;

import com.project.springBootProject.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long>, PagingAndSortingRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findByName(String name);
}
