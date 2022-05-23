package jpamapping.mapping.highLevel.mappedSuper;

import jpamapping.mapping.highLevel.strategy.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mapping");

        EntityManager em = emf.createEntityManager();

        //트랜잭션
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {

            Member member = new Member();
            member.setUsername("user1");
            member.setCreatedBy("park");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member);

            em.flush();
            em.clear();

            et.commit();

        } catch (Exception e){
            et.rollback();
        } finally {

            em.close();
            emf.close();
        }


    }

}
