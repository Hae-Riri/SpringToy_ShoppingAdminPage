package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"user","item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;

    //OrderDetail의 입장에서 생각해야해해
    //OrderDetail : user = N:1이야, 여기서 자기는 n인 상태

    @ManyToOne
    private User user; //알아서 user_id찾아감

    @ManyToOne
    private Item item;
}
