package jpabook.jpql.jpql;


import jpabook.valueType.collection.AddressEntity;
import org.w3c.dom.ls.LSInput;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Collection;
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
            /*
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


             */

            /*

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

            */

            //============ 조인 ================================
            /*
            Team team = new Team();
            team.setName("T1");
            em.persist(team);


            Member member = new Member();
            member.setUsername("m1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            member.setTeam(team);
            em.persist(member);


            em.flush();
            em.clear();

             */

            //inner 조인
            //String q = "select m from Member m inner join m.team t";

            //left 조인
            //String q = "select m from Member m left outer join m.team t";

            //세타조인
            /*String q = "select m from Member m, Team  t where m.username = t.name";
            List<Member> result = em.createQuery(q, Member.class)
                    .getResultList();*/


            //================= 서브쿼리 =====================
            // JPA는 where, having절에서만 서브 쿼리 사용이 가능하다
            // 하이버네이트에서는 select절도 사용 가능

            //select 절 쿼리
            /*String q = "select (select avg(m1.age) FROM Member m1) as avgAge from Member m join Team  t on m.username = t.name";
            List<Member> result = em.createQuery(q, Member.class)
                    .getResultList();

             */

            //====== JPQL 타입 표현 ===================================

            /*
            String q = "select m.username, 'HELLO', TRUE From Member m where m.team = jpabook.jpql.jpql.MemberType.ADMIN";
            List<Object[]> result =  em.createQuery(q)
                    .getResultList();

            for (Object[] o : result){
                System.out.println("Object = " + o[0]);
                System.out.println("Object = " + o[1]);
                System.out.println("Object = " + o[2]);
            }
            */

            //============= 조건식 - CASE =========================
            /*
            String q = "select " +
                        "case when m.age <= 10 then '학생요금' " +
                        "     when m.age >= 60 then '경로요금' " +
                        "     else '일반요금'" +
                        " end " +
                    "from Member m";
            List<String> res = em.createQuery(q, String.class).getResultList();
            for (String s : res){
                System.out.println("s = " + s);
            }

             */

            //=========== JPQL 기본 함수 ==============================
            //String q = "select 'a' || 'b' FROM Member m";

            /*String q = "select locate('a','dfgfsda') FROM Member m";
            List<Integer> res = em.createQuery(q, Integer.class).getResultList();
            for (Integer s : res){
                System.out.println("s = " + s);
            }*/

            //SIZE  ==> JPA 전용
            //String q = "select size(t.members) FROM Team t";

            //INDEX ==> JPA 전용
            /*
            String q = "select index(t.members) FROM Team t";
            List<String> res = em.createQuery(q, String.class).getResultList();
            for (String s : res){
                System.out.println("s = " + s);
            }
             */

            // =========== 경로 표현식 특징 ==========================

            //1.단일 값 연관경로 , 묵시적 내부 조인이 발생한다.
            //Team과 Member 테이블이 조인됨
            //실제 운영시에 주의 해야함 ==> "명시적 조인"을 사용하쟈!!
            //String q = "select m.team.name FROM Member m";


            //2. 컬렉션 값 연관 경로
            //묵시적 내부 조인 발생, 탐색하지 않음
            /*
            String q = "select t.members FROM Team t";

            Collection resQ = em.createQuery(q, Collection.class).getResultList();
            for (Object s : resQ){
                System.out.println("s = " + s);
            }

            //from절에서 명시적 조인을 통해 별칭을 얻으면, 그 별칭을 통해 탐색이 가능
            String q2 = "select m From Team t join t.members m";
            List<String> res = em.createQuery(q2, String.class).getResultList();
            for (String s : res){
                System.out.println("s = " + s);
            }

             */


            //=========== 페치 조인 ========================================

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);


            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            /*
            String q = "select m from Member m";
            List<Member> result = em.createQuery(q, Member.class).getResultList();

            for(Member m : result) {
                System.out.println("m = " + m.getUsername() +" " +m.getTeam().getName());
                //1. 회원1, 팀A (sql)
                //2. 회원2, 팀A(1차 캐시)
                //3. 회원3, 팀B (sql)

                //즉, 이러한 경우는 N개를 조회할때 총 쿼리를 N+1 번 실행하게 되는 것이다!!
            }

            //* 페치 조인 적용
            //조인을 이용해서 한방쿼리를 작성~!!
            String q2 = "select m from Member m join fetch m.team";
            List<Member> result2 = em.createQuery(q2, Member.class).getResultList();

            for(Member m : result2) {
                System.out.println("m = " + m.getUsername() +" " +m.getTeam().getName());
            }
            */


            //컬렉션 페치 조인
            // *** 페치조인 특징과 한계 ***
            // - 페치조인 대상에는 별칭을 줄 수 없음
            // ---- 페치조인 자체가 한방쿼리이기 때문에 따로 별칭을 사용하여 일부 원하는 데이터를 가져올 수 없다.
            // ---- 하이버네이트는 가능하지만, 가급적 사용하지 않는 것을 권고한다.

            // - 둘 이상의 컬렉션은 페치 조인을 할 수 없음
            /*List<Team> aa = em.createQuery("select t from Team t", Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();*/
                /*
                *
                * @BatchSize(size = 100) 배치 사이즈 사용
                * -> List에 담긴 team객체를 한번에 설정해둔 사이즈 만큼 가져옴
                * -> 해당 어노테이션을 통해 페치조인을 사용하지 않을때 N+1 쿼리가 생성되는 상황을 방지
                *
                * persistence.xml에서도 설정 가능
                *
                *
                * */


            // - 컬렉션을 페치조인하면 페이징API를 사용할 수 없다.

           /* String q2 = "select t from Team t join fetch t.members";
            List<Team> result2 = em.createQuery(q2, Team.class).getResultList();

            for(Team t : result2) {
                System.out.println("t = "+t.getName() +" || m ="+t.getMembers().size());
                *//*
                * t = 팀A || m =2
                * t = 팀A || m =2  ==> 중복 출력!!
                * t = 팀B || m =1
                * *//*
            }*/

            //distinct로 증복 없애기 (1 : N)

            /*String q1 = "select distinct t from Team t join fetch t.members";
            List<Team> result1 = em.createQuery(q1, Team.class).getResultList();

            for(Team t : result1) {
                System.out.println("t = "+t.getName() +" || m ="+t.getMembers().size());
                *//*
                    t = 팀A || m =2  => JPA 한번더 중복 엔티티를 제거해줌
                    t = 팀B || m =1

                    제대로
                 * *//*
            }*/


            // ============== 엔티티 직접 사용하기 ==================


            //1. 기본 키 값
            // 엔티티를 직접 사용하게 되면 JPA는 구분자를 PK로 알아서 구분하여 조회
           /* String q = "select m from Member m where m = :member";
            String q2 = "select m from Member m where m.id = :memberId"; //q와 동일한 쿼리 실행
            Member result1 = em.createQuery(q, Member.class)
                    .setParameter("member",member1)
                    .getSingleResult();

            System.out.println("m = "+result1);*/
            /*
            select
            member0_.id as id1_0_,
                    member0_.age as age2_0_,
            member0_.TEAM_ID as TEAM_ID5_0_,
                    member0_.type as type3_0_,
            member0_.username as username4_0_
                    from
            Member member0_
            where
            member0_.id=?
             */


            //2. 외래 키 값
            //teamId 가 PK 값으로, 구분되어 쿼리 실행
            /*String q3 = "select m from Member m where m.team = :team"; //q와 동일한 쿼리 실행
            List<Member> result3 = em.createQuery(q3, Member.class)
                    .setParameter("team",teamA)
                    .getResultList();

            for(Member m : result3){
                System.out.println("m = "+m);
            }*/


            // ============== Named 쿼리 ==================
            // 쿼리에 이름을 부여할 수 있음
            // - 정적쿼리
            // - 애플리케이션 로딩 시점에 초기화 후 재사용
            // ---- sql로 파싱되어 실행
            // - 애플리케이션 로딩 시점에 쿼리를 검증할 수 있음
/*

            List<Member> result = em.createNamedQuery("Member.findByUsername",Member.class)
                    .setParameter("username","회원1")
                    .getResultList();

            for(Member m : result){
                System.out.println("m = "+m);
            }
*/


            // ============== 벌크연산 ==================
            //sql에 update문 delete문
            //쿼리 한 번으로 여러 테이블 로우 변경(엔티티)

            //flush 자동 호출
            int resCnt = em.createQuery("update Member m set m.age = 20") //디비에만 반영했기 때문에, 영속성 컨텍스트를 무시
                            .executeUpdate();

            em.clear(); // 벌크 연산 수행 후 영속성 컨텍스트 초기화

            System.out.println("resCnt = "+resCnt);

            // --< 주의 > --
            //1. 벌크연상은 영속성 컨텍스트를 무시하고 데이터베이스에 직접 쿼리한다.
            // --- 벌크 연산을 먼저 실행
            // --- 벌크 연산 수행 후 영속성 컨텍스트 초기화

            //spring에서는 @Modifying 사용용

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
