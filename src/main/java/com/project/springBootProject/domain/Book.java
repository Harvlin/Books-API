package com.project.springBootProject.domain;

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
public class Book {

    @Id
    String isbn;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

}
