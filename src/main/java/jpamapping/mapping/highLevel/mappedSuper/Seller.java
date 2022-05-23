package jpamapping.mapping.highLevel.mappedSuper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Seller extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "SELLER_ID")
    private Long id;

    @Column(name = "SELLER_NAME")
    private String username;

}
