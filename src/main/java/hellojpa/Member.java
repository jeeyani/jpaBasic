package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity //(name="Member") //jpa가 내부적으로 구분하는 값
//@Table (name="MBR") //"MBR"이라는 이름의 테이블과 매핑
public class Member {

    /*
    @Id
    private Long id;
    @Column(unique = true, length = 10) //제약조건 설정이 가능
    private String name;
    
    //private int age;
     */

    @Id
    private Long id;

    //@Column(name = "name", insertable = false, updatable = false, columnDefinition=" varchar(100) default ‘EMPTY'") //컬럼명 name과 매핑
    @Column(name = "name")
    private String username;

    private int age;

    @Enumerated(EnumType.STRING)
    //@Enumerated(EnumType.ORDINAL) //ORDINAL 순서를 저장하지만, 사용하지 않는 것을 권고
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //날짜타입
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //최신버전은 따로 지정해줄 필요가 없음
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob //큰 컨텐츠
    private String description;

    @Transient
    private int temp;

    public Member(){

    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
