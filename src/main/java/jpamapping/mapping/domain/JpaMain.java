package jpamapping.mapping.domain;

import javax.persistence.*;
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
/*
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");

            //객체관계 매핑
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();


            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            //*** 양방향 연관관계
            Member findMember2 = em.find(Member.class, member.getId());
            List<Member> members = findMember2.getTeam().getMembers();

            for(Member m : members){
                System.out.println("m = " + m.getName());
            }
        */


            //*** 양방향 연관관계 -2
            // 양방향 매핑시 가장 많이 하는 실수
            Team team = new Team();
            team.setName("TeamA");
            //team.getMembers().add(member); //가짜매핑이라 데이터가 들어가지 않음
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
           // member.setTeam(team); //실제 데이터가 들어가는 실제 매핑

            em.persist(member);

            //team.getMembers().add(member); //알아서 값을 넣어주지만 명시용? -> setTeam() 메소드 안에 미리 설정해 두기
            team.addMember(member); //*연관관계 편의 메소드 사용

            em.flush();
            em.clear();

            //값을 따로 넣어주지 않아도 생성하는 순간 값을 알아서 넣어줌 (1차 캐시)
            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            /*
            for(Member m : members){
                System.out.println("m = " + m.getName());
            }
            */

            //**무한루프 주의!!
            System.out.println("==========================");
            System.out.println("members = "+findTeam);
            System.out.println("==========================");

            et.commit();

        } catch (Exception e){
            et.rollback();
        } finally {

            em.close();
            emf.close();
        }


    }

}
