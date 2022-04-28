package io.labs.springreact.jpa.model;

import io.labs.springreact.jpa.constant.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;//주문상태

    private LocalTime orderDate;     //주문시간

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member; //private Long memberId;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;  //배송정보

}
