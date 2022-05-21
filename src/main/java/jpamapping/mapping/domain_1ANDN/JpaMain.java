package jpamapping.mapping.domain_1ANDN;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mapping");

        EntityManager em = emf.createEntityManager();

        //트랜잭션
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {

            Member member = new Member();
            //Member member1 = saveMember(em);
            member.setUsername("member1");

            em.persist(member);

            Team team = new Team();
            team.setName("teamA");

            //member테이블이 업데이트 됨(성능상 단점)
            //현업 운영업무에 있어, 이러한 상황은 테이블을 관리하기에 어려움이 있을 것이다. (권장X)
            team.getMembers().add(member);
            em.persist(team);

            et.commit();

        } catch (Exception e){
            et.rollback();
        } finally {

            em.close();
            emf.close();
        }


    }

}
