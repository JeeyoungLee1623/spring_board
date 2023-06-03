package com.example.spring_board.post.controller;
import com.example.spring_board.author.controller.AuthorController;
import com.example.spring_board.author.domain.Author;
import com.example.spring_board.author.service.AuthorService;
import com.example.spring_board.post.domain.Post;
import com.example.spring_board.post.etc.PostRequestDto;
import com.example.spring_board.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;


    @GetMapping("posts/new")
    public String postCreateForm(){
        return "post/post-register";
    }
    @PostMapping("posts/new")
    public String postCreate(
            PostRequestDto postRequestDto) throws SQLException {
        Author author1 = authorService.findByEmail(postRequestDto.getEmail());
        Post post1 = Post.builder()
                .title(postRequestDto.getTitle())
                .contents(postRequestDto.getContents())
//                post 에는 author 라는 변수가 있으므로, post 생성시 author 객체를 넘겨주면,
//                내부적으로 author 객체에서 author_id 를 꺼내어 DB에 넣게 된다.
                .author(author1)
                .build();
        postService.create(post1);
        return "redirect:/";
    }


    @GetMapping("posts")
    public String postFindAll(Model model) throws SQLException {
        List<Post> posts = postService.findAll();
        model.addAttribute("postList", posts);
        return "post/post-list";
    }
    @GetMapping("post")
    public String postFindById(@RequestParam(value = "id")Long myId, Model model) throws Exception {
        Post post = postService.findById(myId);
        model.addAttribute("post", post);
        return "post/post-detail";
    }

    @PostMapping("post/update")
    public String postUpdate(PostRequestDto postRequestDto)
            throws Exception {
        postService.update(postRequestDto);
        return "redirect:/";
    }
    @GetMapping("post/delete")
    public String deletePost(@RequestParam(value = "id")String id){
        postService.delete(Long.parseLong(id));
        return "redirect:/posts";
    }

























}
