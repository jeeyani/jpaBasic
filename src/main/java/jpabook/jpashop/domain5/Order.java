package jpabook.jpashop.domain5;

import jpabook.jpashop.domain5.Delivery;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    //외래키를 참고하여 연관관계 주인 설정
    @ManyToOne(fetch = FetchType.LAZY) //지연로딩 설정
    @JoinColumn(name = "MEMBER_Id")
    private Member member;

    //order와의 연관관계 설정
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //영속성 전이 설정 완료
    private List<OrderItem> orderItems = new ArrayList<>();

    // 일대일
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//지연로딩 설정 , order를 저장하면 자동적으로 Delivery 저장
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private Date orderDate; //
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member){
        if(this.member != null){
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
