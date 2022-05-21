package jpamapping.mapping.domain_1and1;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /* 일대다 양방향 -> Member에서도 team을 조회하자*/
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) //insert, update를 하지 않도록 설정하여, 조회만 할 수 있도록 한다
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;


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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }
}
