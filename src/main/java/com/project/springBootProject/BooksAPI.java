package com.project.springBootProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.project.springBootProject.domain")
public class BooksAPI {

	public static void main(String[] args) {
		SpringApplication.run(BooksAPI.class, args);
	}
}
