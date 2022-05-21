package jpamapping.mapping.domain_Nand1;

import javax.persistence.*;

//@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /*
     * 객체관계 매핑
     *
     * teamId를 FK로 사용용
     */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

}
