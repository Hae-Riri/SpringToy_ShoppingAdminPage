package com.example.study.model.entity;

import com.example.study.model.enumclass.OrderType;
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
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"user","orderDetailList"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;//주문의 형태 : 일괄?개별발송?

    private String revAddress;

    private String revName;//수신인

    private String paymentType;//카드?현금?

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    @CreatedDate //자동으로 갱신 됨
    private LocalDateTime createdAt;

    @CreatedBy//최초 생성시에 자동으로 생성 돼
    private String createdBy;

    @LastModifiedDate//우리가 지정안해도 엔티티에 수정이 일어나면 자동으로 값 채워짐
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;


    //OrderGroup : User = n:1
    @ManyToOne
    private User user;
    //변수명은 User의 mapped에 적힌 것과 동일하게

    //orderGroup 1 : N orderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;

}
