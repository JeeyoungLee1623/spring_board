package com.example.spring_board.author.controller;

import com.example.spring_board.author.domain.Author;
import com.example.spring_board.author.etc.AuthorRequestDto;
import com.example.spring_board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;


    @GetMapping("/")
    public String Home() {
        return "home";
    }

    // PostMapping 을 통해 AuthorService 호출하   는 Method 생성


    @GetMapping("authors/new")
    public String authorCreateForm(){
        return "author/author-register";
    }
    @PostMapping("authors/new")
//    input 값을 form-data 로 받는 형식
    public String authorCreate(
            AuthorRequestDto authorRequestDto) throws SQLException {
//            @RequestParam(value = "name")String myName,
//            @RequestParam(value = "email")String myEmail,
//            @RequestParam(value = "password")String myPassword,
//            @RequestParam(value = "role")String myRole


//        방법 1 : setter 방식 : 최초 시점 이외의 다른 클래스에서 객체값을 변경함으로서, 유지보수의 어려움이 발생함
//        author1.setName(authorRequestDto.getName());
//        author1.setEmail(authorRequestDto.getEmail());
//        author1.setPassword(authorRequestDto.getPassword());
//        author1.setRole(authorRequestDto.getRole());
//        author1.setCreateDate(LocalDateTime.now());

//       방법 2 : 생성자 방식 (setter 배제)
//       실무에서는 setter 최대한 배제
//       이유는 최초 객체 생성시점 뿐만 아니라, 여러군데에서 setter 를 통해 객체값이 변경가능하게 되어,
//        데이터의 정확성을 보장하기 어렵고, 유지보수가 어렵다.
//        문제점은 반드시 매개변수의 순서를 맞춰줘야 한다는 점이고, 매개변수가 많아지게 되면 개발의 어려움.
//        Author author1 = new Author(
//                authorRequestDto.getName(),
//                authorRequestDto.getEmail(),
//                authorRequestDto.getPassword(),
//                authorRequestDto.getRole()
//        );
//      방법 3 : builder 패턴 : 매개변수의 순서와 상관없이 객체 생성가능
        Author author1 = Author.builder()
                .password(authorRequestDto.getPassword())
                .name(authorRequestDto.getName())
                .email(authorRequestDto.getEmail())
                .role(authorRequestDto.getRole())
                .build();

        authorService.create(author1);
        return "redirect:/";
    }

    @GetMapping("authors")
    public String authorFindAll(Model model) throws SQLException {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authorList", authors);
        return "author/author-list";
    }

    @GetMapping("author")
    public String authorFindById(@RequestParam(value = "id")Long myId, Model model) throws Exception {
        Author author = authorService.findById(myId);
        model.addAttribute("author", author);
        return "author/author-detail";
    }


    @PostMapping("author/update")
    public String authorUpdate(AuthorRequestDto authorRequestDto)


//            (@RequestParam(value = "id")String myId,
//             @RequestParam(value = "name")String myName,
//             @RequestParam(value = "email")String myEmail,
//             @RequestParam(value = "password")String myPassword,
//             @RequestParam(value = "role")String myRole)
             throws Exception {
//        Author author1 = new Author();
//        author1.setId(Long.parseLong(myId));
//        author1.setName(myName);
//        author1.setEmail(myEmail);
//        author1.setPassword(myPassword);
//        author1.setRole(myRole);
        authorService.update(authorRequestDto);
        return "redirect:/";
    }

    
//    DeleteMapping 을 사용할 수도 있지만 DeleteMapping 은 form 태그에서는 지원하지 않는다.
//    form 태그에서 DeleteMapping 을 지원하지 않는다는 말은 action = "delete" 를 줄수 없다는 뜻.
//    그래서, react 나 vue.js 와 같은 프론트엔드의 특정한 기술을 통해서 delete 요청을 일반적으로 하므로, 
//    rest api 방식의 개발(json) 에서는 DeleteMapping 이 가능하다. 
    @GetMapping("author/delete")
    public String deleteAuthor(@RequestParam(value = "id")String id){
        authorService.delete(Long.parseLong(id));
        return "redirect:/authors";
    }








}
