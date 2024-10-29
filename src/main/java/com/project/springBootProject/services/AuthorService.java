package com.project.springBootProject.services;

import com.project.springBootProject.domain.entities.AuthorEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity author);
}
