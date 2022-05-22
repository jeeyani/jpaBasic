package jpabook.domain3.domain2;

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

            Order order = new Order();

            //방법1
            //order.addOrderItem(new OrderItem());


            //방법2 - 단방향성만 설정되어도 크게 상관없기 때문에 가능.
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            em.persist(orderItem);

            et.commit();

        } catch (Exception e){
            et.rollback();
        } finally {

            em.close();
            emf.close();
        }


    }

}
