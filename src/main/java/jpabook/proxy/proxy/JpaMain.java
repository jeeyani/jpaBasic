package jpabook.proxy.proxy;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mapping");

        EntityManager em = emf.createEntityManager();

        //트랜잭션
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {

            /*프록시 기초*/

            /*
            Member member = new Member();
            member.setName("지연");

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class,member.getId());

            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());

            Member findMember2 = em.getReference(Member.class, member.getId());
            System.out.println("findMember.getClass() = " + findMember2.getClass());

            em.detach(findMember2); // 영속성 컨테스트 종료

            System.out.println("findMember.getId() = " + findMember2.getId());
            System.out.println("findMember.getName() = " + findMember2.getName()); //오류발생
            */



            /*즉시로딩 지연로딩*/
            Team team = new Team();
            team.setName("tA");
            em.persist(team);


            Member member1 = new Member();
            member1.setName("m1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member1.getId());
            System.out.println("====================");

            //org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl 오류...왜?
            System.out.println("m = " + m.getTeam().getClass());
            System.out.println("====================");




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
