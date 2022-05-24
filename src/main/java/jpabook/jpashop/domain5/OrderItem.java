package jpabook.jpashop.domain5;

import jpabook.jpashop.domain5.BaseEntity;
import jpabook.jpashop.domain5.Item;
import jpabook.jpashop.domain5.Order;

import javax.persistence.*;

//@Entity
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long Id;


    //연관관계 주인 설정
    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    //연관관계 주인 설정
    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private int orderPrice;
    private int count; //구매수량 합

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}


