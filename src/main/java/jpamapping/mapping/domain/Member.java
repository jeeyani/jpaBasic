package jpamapping.mapping.domain;

import javax.persistence.*;

@Entity
public class Member {

        @Id
        @GeneratedValue
        @Column(name="MEMBER_ID")
        private Long id;

        @Column(name = "USERNAME")
        private String username;

        /*
        ** 테이블 연관관계
        @Column(name = "TEAM_ID")
        private Long teamId;
         */

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

    /*
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
    */

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        //*김영한씨 추천방법
        team.getMembers().add(this);
    }
}
