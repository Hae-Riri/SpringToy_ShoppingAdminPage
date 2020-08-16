package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;


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

    @Test
    @Transactional
    public void read(){
        Optional<User> user = userRepository.findById(4L);

        //4를 가져와서 select를 한 담에 방금 연결시킨 1:n관계의 리스트를 가져와서
        //detail에 내역을 하나씩 넣음
        user.ifPresent(selectUser ->{

            //리스트 형태로 상품이 반환돼, 주문내역이 나옴
            selectUser.getOrderDetailList().stream().forEach(detail ->{
                Item item = detail.getItem();
                System.out.println(item);
            });
        });

        return;

    }

    @Test
    @Transactional
    public void update(){
        Optional<User>user = userRepository.findById(2L);
        //select되었을 때 해당 아이디가 있는지부터 확인하고 이미 존재하면 업데이트해줌
        //그래서 select문이 두 개야(하나는 findById,하나는 selectUser
        user.ifPresent(selectUser ->{
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("updated mathod()");

            userRepository.save(selectUser);
        });

    }

    @Test
    @Transactional
    public void delete(){
        Optional<User>user = userRepository.findById(3L);

        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectUser ->{
            //delete는 void반환임
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assert.assertFalse(deleteUser.isPresent());

    }
}
