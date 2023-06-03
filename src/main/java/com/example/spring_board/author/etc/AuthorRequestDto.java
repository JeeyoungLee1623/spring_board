package com.example.spring_board.author.etc;

// input 태그에 name = "" 로 넘겨주는 name 을 그대로 RegisterForm 에서 사용해야한다.
// 유저와 주고받는 data Form 클래스를 일반적으로 DTO(Date Transfer Object) 라고 부른다.

import lombok.Getter;
import lombok.Setter;

@Getter
// 내부적으로 Setter 를 사용해서 html 의 input 값을 꺼내어 담으므로, setter 가 필요
@Setter
public class AuthorRequestDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
}
