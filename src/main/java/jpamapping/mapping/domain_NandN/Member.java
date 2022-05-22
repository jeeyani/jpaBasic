package jpamapping.mapping.domain_NandN;

import net.bytebuddy.matcher.FilterableList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /* 다대다 */
    @ManyToMany
    @JoinColumn(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();

    /* 연결 엔티티 추가 */
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

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

}
