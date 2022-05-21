package jpamapping.mapping.domain_1and1;

import javax.persistence.*;

@Entity
public class Locker {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // mappedBy 원리 그래도 적용, 읽기 전용으로 사용된다.
    @OneToOne(mappedBy =  "locker")
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

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

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }
}
