package jpabook.jpql.jpql;


import jpabook.valueType.collection.Address;
import jpabook.valueType.collection.AddressEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mapping");

        EntityManager em = emf.createEntityManager();

        //트랜잭션
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {

            //1. JPQL 예시
            /*
            List<Member> result = em.createQuery(
                    "select m from Member m where m.username like '%park%'",
                    Member.class
            ).getResultList();

            for(Member member : result){
                System.out.println("member = " + member);
            }
            */


            //Criteria 사용 준비
            /*CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            //CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "park"));

            //동적쿼리를 작성하는데 편리
            //하지만 코드가 sql 스럽지 않다는 단점이 존재, 유지보수가 어려움
            CriteriaQuery<Member> cq = query.select(m);

            String username = "sdfsdf";
            if(username != null){
                cq = cq.where(cb.equal(m.get("username"), "park"));
            }

            List<Member> resultList = em.createQuery(cq).getResultList();*/

            //네이티브 SQL
            em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from member "). getResultList();

            et.commit();

        } catch (Exception e){
            et.rollback();
            e.printStackTrace();
        } finally {

            em.close();
            emf.close();
        }


    }

}
