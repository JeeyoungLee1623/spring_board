package com.example.spring_board.author.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
//@Setter (생성자를 만들었으므로 필요 없음)
@Entity
// 기본 생성자를 만들어주는 어노테이션
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(length = 50)
    private String name;
    @Setter
    @Column(unique = true, length = 50)
    private String email;
    @Setter
    @Column(length = 20)
    private String password;

    @Setter
    @Column(length = 10)
    private String role;

//    mysql 에서는 datetime 형식으로 컬럼 생성
    @Column
    private LocalDateTime createDate;


//    생성자 방식과 builder 패턴
    @Builder
    public Author(String name, String email, String password, String role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createDate = LocalDateTime.now();
    }





}
