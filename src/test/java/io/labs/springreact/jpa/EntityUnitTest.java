package io.labs.springreact.jpa;

import io.labs.springreact.jpa.constant.OrderStatus;
import io.labs.springreact.jpa.model.Item;
import io.labs.springreact.jpa.model.Member;
import io.labs.springreact.jpa.model.Order;
import io.labs.springreact.jpa.model.OrderItem;
import io.labs.springreact.jpa.repository.ItemRepository;
import io.labs.springreact.jpa.repository.MemberRepository;
import io.labs.springreact.jpa.repository.OrderItemRepository;
import io.labs.springreact.jpa.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Arrays;

@Slf4j
@Transactional
@SpringBootTest
public class EntityUnitTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    public void init(){
        Member member1 = memberRepository.save(Member.builder()
                .name("hyungeun.jin")
                .city("경기도")
                .street("태평동")
                .zipcode("12345")
                .build());
        Member member2 = memberRepository.save(Member.builder()
                .name("legend cup")
                .city("서울")
                .street("중구")
                .zipcode("12345")
                .build());
        memberRepository.saveAll(Arrays.asList(member1, member2));

        Item item1 = itemRepository.save(Item.builder()
                .name("썬그라스 v3")
                .price(100000)
                .stockQuantity(50)
                .build());
        Item item2 = itemRepository.save(Item.builder()
                .name("Z 플립")
                .price(980000)
                .stockQuantity(1)
                .build());
        itemRepository.saveAll(Arrays.asList(item1, item2));

        Order order1 = Order.builder()
                .member(member1)
                .status(OrderStatus.ORDER)
                .orderDate(LocalTime.now())
                .build();

        Order order2 = Order.builder()
                .member(member2)
                .status(OrderStatus.ORDER)
                .orderDate(LocalTime.now())
                .build();
        orderRepository.saveAll(Arrays.asList(order1, order2));

        OrderItem orderItem1 = OrderItem.builder()
                .order(order1)
                .item(item1)
                .count(1)
                .build();

        OrderItem orderItem2 = OrderItem.builder()
                .order(order2)
                .item(item2)
                .count(1)
                .build();

        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2));
    }


    @Test
    public void 테스트(){
        log.debug("초기 데이터 구성.");
        init();
    }
}
