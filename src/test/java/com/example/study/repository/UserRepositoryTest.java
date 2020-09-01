package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;


public class UserRepositoryTest extends StudyApplicationTests {

    //객체를 만들 땐 Builder로, 업데이트할땐 chain을 활용해서!

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        String account = "Test01";
        String password = "Test01";
        UserStatus status = UserStatus.REGISTERED;
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        //@Builder 사용예
        User u = User.builder().
                account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();


        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");


        if(user!=null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {

                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("--------------주문상세-----------------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());
                });


            });
        }

        Assert.assertNotNull(user);
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
