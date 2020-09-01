package com.example.study.service;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;
    
    //1. request data 가져오기
    //2. user 생성
    //3. 생성된 데이터를 기준으로 UserApiResponse를 만들어서 return해주기

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        //1. request data가져오는 코드
        UserApiRequest userApiRequest = request.getData();

        //2.User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        //3. 생성된 데이터를 기반으로 userApiResponse 리턴하기 ->함수사용


        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
//        //id->repository를 통해서 getOne,getById를 통함

        //user가 오면 userApiResponse를 리턴

        return userRepository.findById(id)
                .map(user -> response(user))
                .map(Header :: OK)
                .orElseGet(
                        ()->Header.ERROR("데이터없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> reqest) {
        //1. data
        UserApiRequest userApiRequest = reqest.getData();

        //2.id->user 데이터를 찾고
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        //optional은 있을수도있고 없을수도 있음
        return optional.map(user -> {
            //3. data -> update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;

            //4. userApiResponse
        })
                .map(user -> userRepository.save(user)) //update일어나는 곳
                .map(user -> response(user)) //userApiResponse가 만들어지는 곳
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("데이터없음"));

    }

    @Override
    public Header delete(Long id) {

        //1. id를 가지고 repository를 통해 user를 찾고
        Optional<User>optional = userRepository.findById(id);
        //2. repository->delete
        return optional.map(user ->{
            userRepository.delete(user);
            return Header.OK();
        })
                .orElseGet(()->Header.ERROR("데이터없음"));

        //3. response return
    }


    private UserApiResponse response(User user){

        //user -> userApiResponse만들어서 리턴하는 메소드임

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unRegisteredAt(user.getUnregisteredAt())
                .build();

        //Header + data 를 return

        return userApiResponse;
    }


}
