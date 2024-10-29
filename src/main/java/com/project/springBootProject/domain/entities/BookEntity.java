package com.project.springBootProject.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;

@Log
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    String isbn;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

}
