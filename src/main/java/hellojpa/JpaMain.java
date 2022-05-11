package hellojpa;


import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //트랜잭션
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {
            //실제 데이터를 조작하는 동작을 기입(code)

            /*멤버 등록*/
            /*
            Member member = new Member();
            member.setId(2L);
            member.setName("박지선");
             */

            /*멤버 수정*/
            /*
            //1. 수정할 데이터 찾기
            Member findMember = em.find(Member.class, 1L);
            System.out.println("찾는 ID : " + findMember.getId());
            System.out.println("찾는 Name : " + findMember.getName());

            //2. 데이터 수정하기
            findMember.setName("박지연2");
            */

            /*회원 삭제*/
            //em.remove(findMember);


            /*JPQL*/
            //객체지향 쿼리
            //테이블 중심이 아닌 객체를 대상으로 검색하는 쿼리이다.
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();


            for (Member member : result){
                System.out.println("member.name : " + member.getName());
            }


            et.commit();
        } catch (Exception e){
            et.rollback();
        } finally {

            em.close();
            emf.close();
        }


    }

}
