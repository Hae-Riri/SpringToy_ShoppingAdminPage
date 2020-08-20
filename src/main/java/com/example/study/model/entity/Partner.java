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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"itemList","category"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;

    private String address;

    private String callCenter;

    private String partnerNumber;

    private String businessNumber;

    private String ceoName;

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



    //partner : item = 1:n, 한 개의 파트너가 여러 개의 item을 가짐
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "partner")
    private List<Item>itemList;

    @ManyToOne
    private Category category;
}
