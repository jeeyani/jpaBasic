package jpamapping.mapping.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    private String name;

    /*양방향 연관관계*/

    @OneToMany(mappedBy = "team") //무엇고 연결되어 있는가
    //해당 객체에 값을 넣어도 db에 반영되지 않음
    private List<Member> members = new ArrayList<>();

    public void addMember(Member member){
        member.setTeam(this);
        members.add(member);
    }

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    //무한루프 주의
    @Override
    public String toString(){
        return "Team{"+
                "id="+ id +
                ", name='"+ name + '\'' +
                ", members=" + members +
                '}';
    }
}
