package com.example.spring_board.post.domain;

import com.example.spring_board.author.domain.Author;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    @Column
    private String contents;

//  author 테이블의 id를 fk 로 잡는다
//  author_id 의 type 이 Author 가 됨에 유의. 
//  author_id 는 author 테이블의 id 에 fk 가 걸려있음을 Author 객체타입을 지정함으로서 맵핑
//  그리고 Author author 여기서 author 라는 변수명은 테이블의 컬럼명이 아니라
//  추후에 post 테이블에서 author 를 조회할때 변수명으로 사용되고, 그 관계성을 ManyToOne 으로 표현
//  Author 테이블 입장에서는 1: N 의 관계이고, Post 테이블의 입장에서는 함사람이 여러개의 글을 쓸 수 있으므로 N : 1 이다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "author_id")
    private Author author;


    @Column()
    private LocalDateTime CreateDate;



}