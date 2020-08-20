package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"orderGroup","item"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;
    @CreatedDate //자동으로 갱신 됨
    private LocalDateTime createdAt;

    @CreatedBy//최초 생성시에 자동으로 생성 돼
    private String createdBy;

    @LastModifiedDate//우리가 지정안해도 엔티티에 수정이 일어나면 자동으로 값 채워짐
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;



    @ManyToOne
    private OrderGroup orderGroup;

    //orderDetail : item = n:1, 내가 먼저야
    //내가 n이니까 Many를 먼저해서 ManyToOne임
    @ManyToOne
    private Item item;
}
