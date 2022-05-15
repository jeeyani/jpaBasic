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

            /*======================*/
            /*      멤버 등록        */
            /*======================*/
            /*
            Member member = new Member();
            member.setId(2L);
            member.setName("박지선");
             */

            /*======================*/
            /*      멤버 수정        */
            /*======================*/
            /*
            //1. 수정할 데이터 찾기
            Member findMember = em.find(Member.class, 1L);
            System.out.println("찾는 ID : " + findMember.getId());
            System.out.println("찾는 Name : " + findMember.getName());

            //2. 데이터 수정하기
            findMember.setName("박지연2");
            */

            /*======================*/
            /*      회원삭제         */
            /*======================*/
            //em.remove(findMember);


            /*======================*/
            /*      JPQL            */
            /*======================*/
            //객체지향 쿼리
            //테이블 중심이 아닌 객체를 대상으로 검색하는 쿼리이다.
            /*
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();


            for (Member member : result){
                System.out.println("member.name : " + member.getName());
            }
            */

            /*======================*/
            /*   영속성 컨텍스트      */
            /*======================*/

            //비영속
            /*
            Member member = new Member();
            member.setId(100L);
            member.setName("영속성");


            //영속
            em.persist(member);  //--> 1차 캐시에 저장됨
             */
            
            //1차 캐시
            //한번 조회한 경우 1차 캐시에서 조회하기 때문에
            //select문은 한번만 실행된다.
            /*
            Member findMember1 = em.find(Member.class, 100L);
            Member findMember2 = em.find(Member.class, 100L);

            //영속 엔티티의 동일성 보장
            System.out.println("동일성보장: "+ (findMember1 == findMember2));


            Member member1 = new Member(150L,"A");
            Member member2 = new Member(160L,"B");
            em.persist(member1);
            em.persist(member2);
             */


            //엔티티 수정
            /*
            Member member =  em.find(Member.class,150L);
            member.setName("AAAAA"); //--> update 쿼리가 실행됨
            
            //em.persist(member); //--> 변경시에도 사용할 필요가 없음
             */

            /*======================*/
            /*        플러시         */
            /*======================*/
            /*
            Member member = new Member(200L, "member200");
            em.persist(member);

            //쓰기 지연 SQL 저장소에 변경이 일어나는 것
            //1차캐시가 지워지는 것이 아니다
            em.flush(); //--> 해당 시점에 디비에 반영됨
             */


            /*======================*/
            /*        준영속         */
            /*======================*/
            /*
            //영속상태
            Member member =  em.find(Member.class,150L);
            member.setName("AZAZAZ");

            //영속성 콘텐츠에서 제외시킴
            //em.detach(member); //-->jpa에서 관리하지 않음
            em.clear();

            //다시 영속성 컨텐츠에 올림
            Member member2 =  em.find(Member.class,150L);

            //커밋이후에 쿼리가 나감(이때 디비에 저장됨)
            */

            /*======================*/
            /*   필드와 컬럼 매핑      */
            /*======================*/
            /*
            Member member = new Member();
            member.setId(3L);
            member.setUsername("C");
            member.setRoleType(RoleType.GUEST);

            em.persist(member);
            */

            /*======================*/
            /*    기본 키 매핑        */
            /*======================*/

            MemberID member = new MemberID();
            //member.setId("ID_A");
            member.setUsername("C");

            em.persist(member);

            et.commit();

        } catch (Exception e){
            et.rollback();
        } finally {

            em.close();
            emf.close();
        }


    }

}
