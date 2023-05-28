package com.example.spring_board.author.controller;

import com.example.spring_board.author.domain.Author;
import com.example.spring_board.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.OptionalInt;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // 회원가입
    public void create(Author author) throws SQLException {
        System.out.println("author repository");
        authorRepository.save(author);
    }

    //   회원 목록 조회
    public List<Author> findAll() throws SQLException {
        List<Author> authors = authorRepository.findAll();

        return authors;
    }

    public  Author findById(Long myId) throws SQLException {
        Author authors = authorRepository.findById(myId).orElse(null);
        return authors;
    }
    //    회원 수정
    public void update(Author author) throws Exception {
        Author author1 = authorRepository.findById(author.getId()).orElse(null);
        if(author1 == null){
            throw new Exception();
        }else{
            author1.setName(author.getName());
            author1.setEmail(author.getEmail());
            author1.setPassword(author.getPassword());
            authorRepository.save(author1);
        }
    }






}
