package com.example.spring_board.author.service;

import com.example.spring_board.author.domain.Author;
import com.example.spring_board.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void create(Author author) throws SQLException {
        System.out.println("author repository");
        AuthorRepository.save(author);
    }



}
