package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//DB의 table과 동일해 물론 @Table도 있지만 클래스명과 테이블명이 같으면 자동매칭임
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    //자기 입장에선 order가 1:N이야, 내가 1이고 order가 n이니까 list로 받기
    //mappedBy를 통한 변수매핑은 OrderDetail에 있는 user변수와 이름이 동일해야해
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderDetail>orderDetailList;

}
