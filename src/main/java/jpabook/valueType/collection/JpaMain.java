package jpabook.valueType.collection;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.*;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mapping");

        EntityManager em = emf.createEntityManager();

        //트랜잭션
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {
            Member member = new Member();
            member.setUsername("wlduis");
            member.setHomeAddress(new Address("c","s","1000"));


            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("스파게티티");

            member.getAddressHistory().add(new Address("o1","ss","1010"));
            member.getAddressHistory().add(new Address("o2","ss","1010"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==============================");

            /* 컬렉션은 지연로딩이 기본값*/

            /*
            Member findMember = em.find(Member.class, member.getId());

            List<Address> addressHis = findMember.getAddressHistory();

            for (Address address : addressHis){
                System.out.println("address = "+address.getCity());
            }

            Set<String> ff = findMember.getFavoriteFoods();
            for (String food : ff){
                System.out.println("food = "+food);
            }

            System.out.println("==============================");
            */


            /* 컬렉션 타입 수정하기 */
            //homeC -> newC
            Member findMember = em.find(Member.class, member.getId());
            //findMember.getHomeAddress().setCity("newC"); //버그 발생가능성 !! (X)

            //아예 새롭게 값을 넣어줘야 한다
            //Address a = findMember.getHomeAddress();
            //findMember.setHomeAddress(new Address("newC",a.getStreet(),a.getZipcode()));


            //치킨 -> 짜장면
            //findMember.getFavoriteFoods().remove("치킨");
            //findMember.getFavoriteFoods().add("짜장면");

            //o1 주소를 바꾸기
            //equals를 이용하여 기존 데이터 지우고 새로 넣기
            //영속성 정의 설정 + 고아객체 설정이 적용됨
            findMember.getAddressHistory().remove(new Address("o1","ss","1010")); //내꺼 이거 왜 안지워지냐
            findMember.getAddressHistory().add(new Address("newC1","ss","1010"));


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
