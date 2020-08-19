package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//DB의 table과 동일해 물론 @Table도 있지만 클래스명과 테이블명이 같으면 자동매칭임
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderGroup"})//lombok이 User class를 toString할 때 이 단어는 제외함
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private String unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;


    //User : OrderGroup = 1:N
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<OrderGroup>orderGroupList;
}
