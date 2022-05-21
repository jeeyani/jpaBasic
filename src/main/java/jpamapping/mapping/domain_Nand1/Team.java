package jpamapping.mapping.domain_Nand1;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;
    private String name;

    //team(연관관계의 주인)을 위해 매핑
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();


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

}
