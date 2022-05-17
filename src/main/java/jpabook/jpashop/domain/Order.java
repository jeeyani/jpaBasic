package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

//@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;


    /*
     * 현재 방식은 객체 설계를 테이블 설계에 맞춘 방식
     * 테이블의 외래키를 객체에 그대로 가져옴
     * 객체 그래프 탐색이 불가능
     * 참조가 없으므로 UML도 잘못됨~~!!
     *
     * */
    @Column(name = "MEMBER_ID")
    private Long memberId;

    //객체지향 스럽지 않은 점을 보안하기 위한 방법

    //private Member member;
    private LocalDateTime orderDate; //관례에 따라 다르게 적용할 수 있음 EX) order_id
    
    @Enumerated(EnumType.STRING) //순서꼬이는 것을 방지
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
