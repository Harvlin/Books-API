package com.project.springBootProject.services.impl;

import com.project.springBootProject.domain.entities.AuthorEntity;
import com.project.springBootProject.repositories.AuthorRepository;
import com.project.springBootProject.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }
}
