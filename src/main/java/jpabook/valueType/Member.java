package jpabook.valueType;

import jpabook.proxy.proxy.Team;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String username;

    //기간
    /*private LocalDateTime startDate;
    private LocalDateTime endDate;*/
    @Embedded
    private Period workperiod;


    //주소
    /*private String city;
    private String street;
    private String zipcode;*/
    @Embedded
    private Address homeAddress;

    //속성 재정의 하기
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street", column = @Column(name ="WORK_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name ="WORK_ZIPCODE"))
    })
    private Address workAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkperiod() {
        return workperiod;
    }

    public void setWorkperiod(Period workperiod) {
        this.workperiod = workperiod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

}
