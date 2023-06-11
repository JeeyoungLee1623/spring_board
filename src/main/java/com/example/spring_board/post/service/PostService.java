package com.example.spring_board.post.service;

import com.example.spring_board.author.domain.Author;
import com.example.spring_board.author.etc.AuthorRequestDto;
import com.example.spring_board.author.service.AuthorService;
import com.example.spring_board.post.domain.Post;
import com.example.spring_board.post.etc.PostRequestDto;
import com.example.spring_board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.security.PrivateKey;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AuthorService authorService;


    public void create(PostRequestDto postRequestDto) throws SQLException {
            String my_appointment = null;
            LocalDateTime my_appointment_time = null;
            if(postRequestDto.getAppointment() != null
                    && !postRequestDto.getAppointment_time().isEmpty()
            ){
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime  localDateTime = LocalDateTime.parse(postRequestDto.getAppointment_time(), dateTimeFormatter);
                LocalDateTime currentTime = LocalDateTime.now();
                if(currentTime.isBefore(localDateTime)==true) {
                    my_appointment_time = localDateTime;
                    my_appointment = "Y";
                }
            }

            Author author1 = authorService.findByEmail(postRequestDto.getEmail());
            Post post1 = Post.builder()
                    .title(postRequestDto.getTitle())
                    .contents(postRequestDto.getContents())
//                post 에는 author 라는 변수가 있으므로, post 생성시 author 객체를 넘겨주면,
//                내부적으로 author 객체에서 author_id 를 꺼내어 DB에 넣게 된다.
                    .author(author1)
                    .appointment(my_appointment)
                    .appointment_time(my_appointment_time)
                    .build();
            postRepository.save(post1);
    }



    public List<Post> findAll() throws SQLException {
        List<Post> posts = postRepository.findByAppointment(null);
        return posts;
    }

    public  Post findById(Long myId) throws EntityNotFoundException {
        Post posts = postRepository.findById(myId).orElseThrow(EntityNotFoundException::new);
        return posts;
    }

    public void update(PostRequestDto postRequestDto) throws Exception {
        Post post1 = postRepository.findById(Long.parseLong(postRequestDto.getId())).orElseThrow(Exception::new);
        if (post1 == null) {
            throw new Exception();
        } else {
            post1.setTitle(postRequestDto.getTitle());
            post1.setContents(postRequestDto.getContents());
            postRepository.save(post1);
        }

    }

    public void delete (Long id) {
//      먼저 DB 에서 조회 후에 author 객체를 가져온다.
//      해당 author 객체로 jpa 가 delete 할 수 있도록 전달해준다.
        postRepository.delete(this.findById(id));
    }



















}
