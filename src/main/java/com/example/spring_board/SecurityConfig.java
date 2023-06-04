package com.example.spring_board;

import com.example.spring_board.author.service.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// EnableWebSecurity 어노테이션은 spring security 를 customizing 기능을 활성화
@EnableWebSecurity
public class SecurityConfig {
//    스프링에서 빈을 만드는 방법 2가지 (둘다 싱글톤) (객체가 자동으로 하나가 만들어져서 여러군데에서 사용하므로 메모리 관리에 도움이 됨)
//    방법1. Component 방식 
//    개발자가 직접 컨트롤이 가능한 내부 클래서에서 사용
    
//    방법2. Configuration + Bean 방식
//    개발자가 컨트롤이 불가능한 외부 라이브러리 적용시 사용
    @Bean
    public SecurityFilterChain myFilter(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
//                보안공격에 대한 설정은 별도로 하지 않겠다라는 뜻
                .csrf().disable()
                .authorizeRequests()
//                login authenticated 에서 제외대한 대상 url 지정
                .antMatchers("/author/login", "/", "/authors/new")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/author/login")
//                spring 의 doLogin 기능 그대로 활용
                    .loginProcessingUrl("/doLogin")
                    .usernameParameter("email")
                    .passwordParameter("password")
//                로그인 성공시 이후 로직 LoginSuccessHandler 에서 구현
                    .successHandler(new LoginSuccessHandler())
                .and()
                .logout()
//                spring 의 doLogout 기능 그대로 활용
                    .logoutUrl("/doLogout")
                .and().build();
    }










}
