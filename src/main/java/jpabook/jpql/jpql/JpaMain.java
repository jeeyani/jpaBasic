package jpabook.jpql.jpql;


import jpabook.valueType.collection.AddressEntity;

import javax.persistence.*;
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
            //em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from member "). getResultList();

            /* JPQL */
            Member member = new Member();
            member.setUsername("m1");
            member.setAge(10);
            em.persist(member);

            //반환 타입이 명확
            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);

            //반환 타입이 불명확
            Query query3 = em.createQuery("select m.username, m.age from Member m", String.class);

            //getSingleResult() : 결과가 정확히 하나만 출력될 때 사용,
            //결과가 없을떄와 둘 이상인 경우 각각 오류를 던짐
            //즉, 결과가 1개가 아니면 예외가 발생
            Member result = query1.getSingleResult();

            //파라미터 바인딩
            TypedQuery<Member> queryBinding = em.createQuery("select m from Member m where m.username = :username", Member.class);
            queryBinding.setParameter("username", "m1");
            Member res = queryBinding.getSingleResult();
            System.out.println("SingleResult = " + res.getUsername());



            //===========프로젝션=================================================

            //엔티티 프로젝션
            em.createQuery("select m.team from Member m", Team.class)
                    .getResultList();

            //임베디드 타입
            em.createQuery("select o.address from Order o", Address.class)
                            .getResultList();

            //스칼렛
            em.createQuery("select distinct m.username, m.age from Member m")
                    .getResultList();

            //스칼렛 타입을 가져오는 방법
            List resultList =  em.createQuery("select distinct m.username, m.age from Member m").getResultList();
            //1. 쿼리타입
            Object o = resultList.get(0);
            Object[] result2 = (Object[]) o;

            //2. 엔티티 타입 조회
            List<Object[]> resultList2 =  em.createQuery("select distinct m.username, m.age from Member m").getResultList();

            //3.new 명령어
            //List<Object[]> resultList3 =  em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m").getResultList();



            //==================  페이징  ==========================================
            List<Member> aa = em.createQuery("select m from Member m order by m.age desc ", Member.class)
                    .setFirstResult(0) //조회시작 위치
                    .setMaxResults(10) //조회할 데이터 수
                    .getResultList();

            for(Member m1 : aa){
                System.out.println("aa = " + aa);
            }



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
