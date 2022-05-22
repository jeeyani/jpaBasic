package jpabook.jpashop.domain3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mapping");

        EntityManager em = emf.createEntityManager();

        //트랜잭션
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {

            et.commit();

        } catch (Exception e){
            et.rollback();
        } finally {

            em.close();
            emf.close();
        }


    }

}
