package com.example.spring_board.author.repository;

import com.example.spring_board.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository <Author, Long> {
//    findByA 라는 규칙으로 jpa 에서는 자동으로 where 조건에 조건문으로 A 를 사용하게 된다.
//    만약 where A and B 를 and 조건으로 사용하고 싶으면 findByAandB 를 사용하면 된다.
    Author findByEmail(String myEmail);

}
