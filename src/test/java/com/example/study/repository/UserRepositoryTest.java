package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserRepositoryTest extends StudyApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        //id는 autoIncrement라 자동으로 넣어지니까 우리가 set하지 않아
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-3333-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        //save함수는 User로 반환해, 얜 id도 달고 나오지
        User newUser = userRepository.save(user);
        System.out.println("new User : "+newUser);
    }

    public void read(){

    }
    public void update(){

    }
    public void delete(){

    }
}
