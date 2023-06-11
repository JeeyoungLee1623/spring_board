package com.example.spring_board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

//    value 라는 어노테이션을 통해 yml 에 있는 정보를 프로그램안으로 끌고 들어올 수 있다.
//    host 또는 port 정보에 변경사항이 생기면 모든 코드를 고칠 필요 없이 yml 에 있는 host, port 정보만 바꾸면 된다.
    @Value("${spring.redis.host}")
    public String host;
    @Value("${spring.redis.port}")
    public int port;

//    Redis DB 에 어떤 형식으로 데이터를 set 할지에 대한 customizing
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

//    redis 의 구성정보에 대한 정보를 spring 에 알려줘야 자동으로 session 데이터를 redis 에 담을 수 있다.
//    현재는 standAlone 단일 구성으로 가고자 한다.
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

















}
