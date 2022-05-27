package jpabook.jpashop.domain6;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    //실전예제2 추가
    //** 예제용 코드일 뿐, 비지니스적으로 너무 비효율적인 코드
    //멤버에서 주문내역을 조회한다는 것이 비효율적임
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    //실전예제3
    @Embedded
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
