package jpamapping.mapping.highLevel.strategy;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) //조인전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //단일 테이블 전략
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //구현 클래스마다의 테이블 전략
@DiscriminatorColumn //DTYPE 자동으로 만들어줌
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long Id;

    private String name;
    private int price;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
