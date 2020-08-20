package com.example.study.service;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiRequest> {

    @Autowired
    private UserRepository userRepository;
    
    //1. request data 가져오기
    //2. user 생성
    //3. 생성된 데이터를 기준으로 UserApiResponse를 만들어서 return해주기

    @Override
    public Header<UserApiRequest> create(Header<UserApiRequest> request) {

        //1. request data가져오는 코드
        UserApiRequest userApiRequest = request.getData();

        //2.user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        //3. 생성된 데이터를 기반으로 userApiResponse 리턴하기 -> 아래로 뺌


        return response(newUser);
    }

    @Override
    public Header<UserApiRequest> read(Long id) {
        return null;
    }

    @Override
    public Header<UserApiRequest> update(Header<UserApiRequest> reqest) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<UserApiResponse> response(User user){

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

        return Header.OK(userApiResponse);
    }


}
