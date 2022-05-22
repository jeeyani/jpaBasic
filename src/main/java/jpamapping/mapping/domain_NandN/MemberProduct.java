package jpamapping.mapping.domain_NandN;

import javax.persistence.*;

/* 연결 엔티티 추가 */
//@Entity
public class MemberProduct {
    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderAmount;

}
