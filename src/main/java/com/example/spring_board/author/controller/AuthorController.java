package com.example.spring_board.author.controller;

import com.example.spring_board.author.domain.Author;
import com.example.spring_board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;


    @GetMapping("/")
    public String Home() {
        return "home";
    }

    // PostMapping 을 통해 AuthorService 호출하는 Method 생성


    @GetMapping("authors/new")
    public String authorCreateForm(){
        return "author/author-register";
    }
    @PostMapping("authors/new")
//    input 값을 form-data 로 받는 형식
    public String authorCreate(@RequestParam(value = "name")String myName,
                               @RequestParam(value = "email")String myEmail,
                               @RequestParam(value = "password")String myPassword,
                               @RequestParam(value = "role")String myRole) throws SQLException {
        Author author1 = new Author();
        author1.setName(myName);
        author1.setEmail(myEmail);
        author1.setPassword(myPassword);
        author1.setRole(myRole);
        author1.setCreateDate(LocalDateTime.now());
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
    public String authorUpdate(@RequestParam(value = "id")String myId,
                               @RequestParam(value = "name")String myName,
                               @RequestParam(value = "email")String myEmail,
                               @RequestParam(value = "password")String myPassword,
                               @RequestParam(value = "role")String myRole) throws Exception {
        Author author1 = new Author();
        author1.setId(Long.parseLong(myId));
        author1.setName(myName);
        author1.setEmail(myEmail);
        author1.setPassword(myPassword);
        author1.setRole(myRole);
        authorService.update(author1);
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
