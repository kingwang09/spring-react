package io.labs.springreact.jpa.model;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    private int count;      //주문 수량

    private int orderPrice; //주문 가격

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item; //private Long itemId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order; //private Long orderId;


    @Builder
    public OrderItem(Item item, Order order, int count){
        this.item = item;
        this.order = order;
        this.count = count;
        this.orderPrice = item.getPrice() * count;
    }
}
