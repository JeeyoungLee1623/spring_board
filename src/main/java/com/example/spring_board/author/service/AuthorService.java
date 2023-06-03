package com.example.spring_board.author.service;

import com.example.spring_board.author.domain.Author;
import com.example.spring_board.author.etc.AuthorRequestDto;
import com.example.spring_board.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.OptionalInt;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // 회원가입
    public void create(Author author) throws SQLException {
        authorRepository.save(author);
    }

    //   회원 목록 조회
    public List<Author> findAll() throws SQLException {
        List<Author> authors = authorRepository.findAll();

        return authors;
    }

    public  Author findById(Long myId) throws EntityNotFoundException {
        Author authors = authorRepository.findById(myId).orElseThrow(EntityNotFoundException::new);
        return authors;
    }
    //    회원 수정
    public void update(AuthorRequestDto authorRequestDto) throws Exception {
        Author author1 = authorRepository.findById(Long.parseLong(authorRequestDto.getId())).orElseThrow(Exception::new);
        if (author1 == null) {
            throw new Exception();
        } else {
            author1.setName(authorRequestDto.getName());
            author1.setEmail(authorRequestDto.getEmail());
            author1.setPassword(authorRequestDto.getPassword());
            author1.setRole(authorRequestDto.getRole());
            authorRepository.save(author1);
        }

    }

    public void delete (Long id) {
//      먼저 DB 에서 조회 후에 author 객체를 가져온다.
//      해당 author 객체로 jpa 가 delete 할 수 있도록 전달해준다.
        authorRepository.delete(this.findById(id));
        }


        public Author findByEmail(String email) {
        return authorRepository.findByEmail(email);
        }








    }







