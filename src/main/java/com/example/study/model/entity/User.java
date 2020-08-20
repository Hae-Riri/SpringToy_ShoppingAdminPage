package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//DB의 table과 동일해 물론 @Table도 있지만 클래스명과 테이블명이 같으면 자동매칭임
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderGroupList"})//lombok이 User class를 toString할 때 이 단어는 제외함
@EntityListeners(AuditingEntityListener.class)
@Builder //객체 생성 시 생성자로 안하고 간단하게 . 연산자로
@Accessors(chain=true)//update할 때 set으로 한줄씩 안써도 .연산자로 계속 바꾸기가능
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

    private LocalDateTime unregisteredAt;

    @CreatedDate //자동으로 갱신 됨
    private LocalDateTime createdAt;

    @CreatedBy//최초 생성시에 자동으로 생성 돼
    private String createdBy;

    @LastModifiedDate//우리가 지정안해도 엔티티에 수정이 일어나면 자동으로 값 채워짐
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;



    //User : OrderGroup = 1:N
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<OrderGroup>orderGroupList;
}
